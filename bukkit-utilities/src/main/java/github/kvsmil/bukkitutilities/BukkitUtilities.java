package github.kvsmil.bukkitutilities;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public final class BukkitUtilities {

    public static String color(@NonNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendMessage(@NonNull CommandSender sender, @NonNull String message) {
        sender.sendMessage(color(message));
    }

    public static void sendMessage(@NonNull CommandSender sender, @NonNull String... messages) {
        for (String message : messages) sendMessage(sender, message);
    }

    public static void sendMessage(@NonNull CommandSender sender, @NonNull List<String> messages) {
        messages.forEach(message -> sendMessage(sender, message));
    }

}
