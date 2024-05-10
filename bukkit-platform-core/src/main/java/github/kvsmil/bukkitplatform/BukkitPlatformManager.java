package github.kvsmil.bukkitplatform;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import eu.okaeri.injector.annotation.Inject;
import github.kvsmil.bukkitplatform.annotation.ConfigurationPath;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.function.Consumer;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public final class BukkitPlatformManager {

    @Getter private static final Injector injector;
    private final BukkitPlatform platform;

    static {
        injector = OkaeriInjector.create();
    }

    public <T> void registerComponent(@NonNull T type) {
        injector.registerInjectable(type);

        boolean isConfiguration = type.getClass().getAnnotation(ConfigurationPath.class) != null;

        if(isConfiguration) {
            this.registerConfiguration((OkaeriConfig) type);
        }

        if(this.platform.isDebug()) {
            this.platform.getLogger().info("Registered injectable component: " + type.getClass().getSimpleName());
        }
    }

    public <T> void registerComponent(@NonNull T type, Consumer<T> consumer) {
        this.registerComponent(type);
        consumer.accept(type);
    }

    public <T> T registerInstance(@NonNull Class<T> type) {
        return injector.createInstance(type);
    }

    private <T extends OkaeriConfig> void registerConfiguration(@NonNull T type) {

        ConfigurationPath path = type.getClass().getAnnotation(ConfigurationPath.class);
        String dataFile = path.path();

        ConfigManager.create(type.getClass(), config -> {
           config.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
           config.withBindFile(new File(this.platform.getDataFolder(), dataFile));
           config.withRemoveOrphans(path.removeOrphans());
           config.withSerdesPack(this.platform.serdesPack());
           config.saveDefaults();
           config.load(true);
        });
    }

}
