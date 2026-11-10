package net.gawharasmp.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public final class GawharaItemsCommand implements TabExecutor {
    private final JavaPlugin plugin;
    private final ItemFactory itemFactory;

    public GawharaItemsCommand(JavaPlugin plugin, ItemFactory itemFactory) {
        this.plugin = plugin;
        this.itemFactory = itemFactory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("menu")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(ChatColor.RED + "Only players can open the item menu.");
                return true;
            }
            if (!player.hasPermission("gawharaitems.use")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this menu.");
                return true;
            }
            ItemMenu.open(player, itemFactory);
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            if (!sender.hasPermission("gawharaitems.admin")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission.");
                return true;
            }
            sender.sendMessage(ChatColor.AQUA + "GawharaItems IDs:");
            for (ItemType type : ItemType.values()) {
                sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.YELLOW + type.getId()
                        + ChatColor.DARK_GRAY + " (" + type.getDisplayName() + ")");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("gawharaitems.admin")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission.");
                return true;
            }
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "GawharaItems configuration reloaded.");
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission("gawharaitems.admin")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission.");
                return true;
            }
            if (args.length < 3) {
                sender.sendMessage(ChatColor.YELLOW
                        + "Usage: /gitems give <player> <item_id> [amount]");
                return true;
            }

            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online.");
                return true;
            }

            ItemType type = ItemType.fromId(args[2]);
            if (type == null) {
                sender.sendMessage(ChatColor.RED + "Unknown item ID. Use /gitems list.");
                return true;
            }

            int amount = 1;
            if (args.length >= 4) {
                try {
                    amount = Integer.parseInt(args[3]);
                } catch (NumberFormatException exception) {
                    sender.sendMessage(ChatColor.RED + "Amount must be a whole number.");
                    return true;
                }
            }
            if (amount < 1 || amount > 64) {
                sender.sendMessage(ChatColor.RED + "Amount must be between 1 and 64.");
                return true;
            }

            for (int i = 0; i < amount; i++) {
                ItemStack item = itemFactory.create(type);
                var leftovers = target.getInventory().addItem(item);
                leftovers.values().forEach(leftover ->
                        target.getWorld().dropItemNaturally(target.getLocation(), leftover));
            }

            String prefix = ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("settings.prefix", "&bGawharaItems &8» "));
            sender.sendMessage(prefix + ChatColor.GREEN + "Gave " + amount + "x "
                    + type.getDisplayName() + " to " + target.getName() + ".");
            target.sendMessage(prefix + ChatColor.GREEN + "You received " + amount + "x "
                    + type.getDisplayName() + ".");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Usage: /gitems [menu|list|give|reload]");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
                                      String alias, String[] args) {
        if (args.length == 1) {
            return List.of("menu", "list", "give", "reload").stream()
                    .filter(value -> value.startsWith(args[0].toLowerCase()))
                    .toList();
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                    .toList();
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
            return Arrays.stream(ItemType.values())
                    .map(ItemType::getId)
                    .filter(id -> id.startsWith(args[2].toLowerCase()))
                    .toList();
        }
        return List.of();
    }
}
