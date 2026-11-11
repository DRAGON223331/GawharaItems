package net.gawharasmp.items;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.util.Locale;
import java.util.regex.Pattern;

/** Sends the Java resource pack to players after they join. */
public final class ResourcePackManager implements Listener {
    private static final Pattern SHA1_PATTERN = Pattern.compile("[a-fA-F0-9]{40}");

    private final JavaPlugin plugin;

    public ResourcePackManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!plugin.getConfig().getBoolean("resource-pack.enabled", true)) {
            return;
        }

        long delay = Math.max(1L, plugin.getConfig().getLong("resource-pack.join-delay-ticks", 40L));
        Player player = event.getPlayer();
        if (isFloodgatePlayer(player)) {
            return; // Bedrock clients receive the separate pack from Geyser's packs directory.
        }
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            if (player.isOnline() && !isFloodgatePlayer(player)) {
                send(player, false);
            }
        }, delay);
    }

    public boolean send(Player player, boolean notifyPlayer) {
        if (isFloodgatePlayer(player)) {
            if (notifyPlayer) player.sendMessage(prefix() + ChatColor.YELLOW + "Bedrock resource packs are supplied by Geyser.");
            return false;
        }
        if (!plugin.getConfig().getBoolean("resource-pack.enabled", true)) {
            if (notifyPlayer) player.sendMessage(prefix() + ChatColor.RED + "Java resource-pack delivery is disabled.");
            return false;
        }

        String configuredUrl = plugin.getConfig().getString("resource-pack.url", "");
        String configuredSha1 = plugin.getConfig().getString("resource-pack.sha1", "");
        String url = configuredUrl == null ? "" : configuredUrl.trim();
        String sha1 = configuredSha1 == null ? "" : configuredSha1.trim().toLowerCase(Locale.ROOT);
        if (url.isEmpty()) {
            if (notifyPlayer) player.sendMessage(prefix() + ChatColor.RED + "The resource pack URL has not been configured by the server owner.");
            return false;
        }

        try {
            URI uri = URI.create(url);
            if (!"https".equalsIgnoreCase(uri.getScheme()) || uri.getHost() == null) {
                throw new IllegalArgumentException("The resource-pack URL must be a direct HTTPS URL.");
            }
        } catch (IllegalArgumentException exception) {
            plugin.getLogger().warning("Resource pack was not sent: " + exception.getMessage());
            if (notifyPlayer) player.sendMessage(prefix() + ChatColor.RED + "The server resource-pack URL is invalid. Contact an administrator.");
            return false;
        }

        if (!SHA1_PATTERN.matcher(sha1).matches()) {
            plugin.getLogger().warning("Resource pack was not sent: resource-pack.sha1 must contain exactly 40 hexadecimal characters.");
            if (notifyPlayer) player.sendMessage(prefix() + ChatColor.RED + "The server resource-pack checksum is invalid. Contact an administrator.");
            return false;
        }

        boolean required = plugin.getConfig().getBoolean("resource-pack.required", false);
        String prompt = plugin.getConfig().getString("resource-pack.prompt", "Download the GawharaSMP custom items pack?");
        Component promptComponent = prompt == null || prompt.isBlank() ? null : Component.text(prompt);

        try {
            player.setResourcePack(url, sha1, required, promptComponent);
            if (notifyPlayer) {
                player.sendMessage(prefix() + ChatColor.GREEN + "Resource-pack request sent. Check the prompt in Minecraft.");
                player.sendMessage(prefix() + ChatColor.GRAY + "No prompt? In the server list choose Edit on this server and set Server Resource Packs to Enabled or Prompt.");
            }
            return true;
        } catch (IllegalArgumentException exception) {
            plugin.getLogger().warning("Could not send resource pack to " + player.getName() + ": " + exception.getMessage());
            if (notifyPlayer) player.sendMessage(prefix() + ChatColor.RED + "Could not send the resource pack. Contact an administrator.");
            return false;
        }
    }

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        switch (event.getStatus()) {
            case SUCCESSFULLY_LOADED -> plugin.getLogger().info("Java resource pack loaded by " + event.getPlayer().getName() + ".");
            case DECLINED -> plugin.getLogger().info(event.getPlayer().getName() + " declined the Java resource pack.");
            case FAILED_DOWNLOAD, INVALID_URL -> plugin.getLogger().warning("Java resource pack download failed for " + event.getPlayer().getName() + " (" + event.getStatus() + "). Check the URL and hosting response.");
            case FAILED_RELOAD -> plugin.getLogger().warning("Java resource pack could not be applied for " + event.getPlayer().getName() + ".");
            default -> { }
        }
    }

    private boolean isFloodgatePlayer(Player player) {
        try {
            Class<?> apiClass = Class.forName("org.geysermc.floodgate.api.FloodgateApi");
            Object api = apiClass.getMethod("getInstance").invoke(null);
            Object result = apiClass.getMethod("isFloodgatePlayer", java.util.UUID.class)
                    .invoke(api, player.getUniqueId());
            return Boolean.TRUE.equals(result);
        } catch (ReflectiveOperationException ignored) {
            // Floodgate is optional. If it is not installed, all online players are treated as Java.
            return false;
        }
    }

    private String prefix() {
        return ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("settings.prefix", "&bGawharaItems &8» "));
    }
}
