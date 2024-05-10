package github.kvsmil.bukkitplatform.user;

import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import lombok.NonNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@DocumentCollection(path = "users", keyLength = 36)
public interface BukkitTemplateUserRepository extends DocumentRepository<UUID, BukkitTemplateUser> {

    default BukkitTemplateUser findOrCreate(@NonNull UUID uuid, String name) {
        BukkitTemplateUser user = this.findOrCreateByPath(uuid);

        if(name != null) {
            user.setName(name);
        }

        return user;
    }

    default Optional<BukkitTemplateUser> findByName(@NonNull String name) {
        return this.findAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst();
    }

}
