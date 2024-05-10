package github.kvsmil.bukkitplatform.persistence;

import com.zaxxer.hikari.HikariConfig;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.persistence.PersistenceCollection;
import eu.okaeri.persistence.PersistencePath;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.jdbc.JdbcPersistence;
import github.kvsmil.bukkitplatform.BukkitPlatform;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public final class BukkitPersistence {

    private final BukkitPlatform platform;

    public DocumentPersistence registerPersistence(@NonNull PersistencePath path, @NonNull HikariConfig hikariConfig) {
        return new DocumentPersistence(new JdbcPersistence(path, hikariConfig), YamlBukkitConfigurer::new, new SerdesBukkit());
    }

    public void registerCollection(@NonNull DocumentPersistence persistence, @NonNull PersistenceCollection... collections) {
        for(PersistenceCollection collection : collections) {
            CompletableFuture.runAsync(() -> persistence.registerCollection(collection));
        }
    }

}
