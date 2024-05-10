package github.kvsmil.bukkitplatform;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import github.kvsmil.bukkitplatform.persistence.BukkitPersistence;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public abstract class BukkitPlatform extends JavaPlugin {

    private BukkitPlatformManager platformManager;
    private BukkitPersistence bukkitPersistence;

    @Setter private boolean debug;

    public abstract void startup(@NonNull BukkitPlatformManager platformManager);
    public abstract void shutdown(@NonNull BukkitPlatformManager platformManager);
    public abstract OkaeriSerdesPack serdesPack();

    @Override
    public void onEnable() {
        this.getLogger().info(String.format("Loading plugin named %s (v%s), Authors: %s", this.getName(),
                this.getDescription().getVersion(),
                this.getDescription().getAuthors()));

        this.platformManager = new BukkitPlatformManager(this);
        this.platformManager.registerComponent(this);
        this.bukkitPersistence = this.platformManager.registerInstance(BukkitPersistence.class);

        this.startup(this.platformManager);
    }

    @Override
    public void onDisable() {
        this.shutdown(this.platformManager);
    }

}