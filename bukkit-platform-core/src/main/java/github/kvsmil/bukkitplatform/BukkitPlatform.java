package github.kvsmil.bukkitplatform;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import github.kvsmil.bukkitplatform.persistence.BukkitPersistence;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.time.Duration;

@Getter
@SuppressWarnings("Unchecked")
public abstract class BukkitPlatform extends JavaPlugin {

    private BukkitPlatformManager platformManager;
    private BukkitPersistence bukkitPersistence;

    @Setter private boolean debug;
    @Setter private BukkitScheduler bukkitScheduler;

    public abstract void startup(@NonNull BukkitPlatformManager platformManager);
    public abstract void shutdown(@NonNull BukkitPlatformManager platformManager);
    public abstract OkaeriSerdesPack getSerdesPack();

    @Override
    public void onEnable() {
        this.getLogger().info(String.format("Loading plugin named %s (v%s), Authors: %s", this.getName(),
                this.getDescription().getVersion(),
                this.getDescription().getAuthors()));

        this.platformManager = new BukkitPlatformManager(this);
        this.platformManager.registerComponent(this);
        this.bukkitScheduler = this.getServer().getScheduler();
        this.bukkitPersistence = this.platformManager.registerInstance(BukkitPersistence.class);

        this.startup(this.platformManager);
    }

    @Override
    public void onDisable() {
        this.shutdown(this.platformManager);
    }

    public void runAsync(@NonNull Runnable runnable, @NonNull Duration time) {
        long millis = 20L * time.toMillis();
        this.bukkitScheduler.runTaskTimerAsynchronously(this, runnable, millis, millis);
    }

    public void runTimer(@NonNull Runnable runnable, @NonNull Duration time) {
        long millis = 20L * time.toMillis();
        this.bukkitScheduler.runTaskTimer(this, runnable, millis, millis);
    }

    public void runLater(@NonNull Runnable runnable, @NonNull Duration time) {
        long millis = 20L * time.toMillis();
        this.bukkitScheduler.runTaskLater(this, runnable, millis);
    }

    public void runLaterAsync(@NonNull Runnable runnable, @NonNull Duration time) {
        long millis = 20L * time.toMillis();
        this.bukkitScheduler.runTaskLaterAsynchronously(this, runnable, millis);
    }

}