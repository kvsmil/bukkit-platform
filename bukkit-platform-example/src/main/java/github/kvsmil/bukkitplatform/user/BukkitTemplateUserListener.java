package github.kvsmil.bukkitplatform.user;

import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.persistence.document.Document;
import github.kvsmil.bukkitplatform.BukkitTemplatePlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public final class BukkitTemplateUserListener implements Listener {

    private final BukkitTemplateUserRepository repository;
    private final BukkitTemplatePlugin plugin;

    @EventHandler
    void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // async user saving
        CompletableFuture.supplyAsync(() -> this.repository.findOrCreate(player.getUniqueId(), player.getName()))
                .thenAcceptAsync(Document::save);

    }

}
