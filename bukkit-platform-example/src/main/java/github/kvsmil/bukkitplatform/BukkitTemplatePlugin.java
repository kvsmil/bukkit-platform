package github.kvsmil.bukkitplatform;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.persistence.PersistenceCollection;
import eu.okaeri.persistence.PersistencePath;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.jdbc.commons.JdbcHelper;
import eu.okaeri.persistence.repository.RepositoryDeclaration;
import github.kvsmil.bukkitplatform.config.BukkitTemplateConfig;
import github.kvsmil.bukkitplatform.persistence.BukkitPersistence;
import github.kvsmil.bukkitplatform.user.BukkitTemplateUserListener;
import github.kvsmil.bukkitplatform.user.BukkitTemplateUserRepository;
import lombok.NonNull;

public class BukkitTemplatePlugin extends BukkitPlatform {

    @Override
    public void startup(@NonNull BukkitPlatformManager platformManager) {
        platformManager.registerComponent(new BukkitTemplateConfig(), configuration -> {
            // debug set for platform logging
            this.setDebug(configuration.debug);

            // persistence and user collection register
            platformManager.registerComponent(platformManager.registerInstance(BukkitPersistence.class), bukkitPersistence -> {
                PersistenceCollection userCollection = PersistenceCollection.of("users");
                PersistencePath persistencePath = PersistencePath.of(configuration.prefix);

                DocumentPersistence documentPersistence = bukkitPersistence.registerPersistence(persistencePath, JdbcHelper.configureHikari(configuration.databaseURI));

                BukkitTemplateUserRepository userRepository = RepositoryDeclaration.of(BukkitTemplateUserRepository.class)
                                .newProxy(documentPersistence, userCollection, this.getClassLoader());

                platformManager.registerComponent(userRepository);
                bukkitPersistence.registerCollection(documentPersistence, userCollection);
            });

        });

        this.getServer().getPluginManager().registerEvents(platformManager.registerInstance(BukkitTemplateUserListener.class), this);
    }

    @Override
    public OkaeriSerdesPack serdesPack() {
        return registry -> {

        };
    }

    @Override
    public void shutdown(@NonNull BukkitPlatformManager platformManager) {
        this.getLogger().info("Disabling plugin, thanks for using...");
    }

}