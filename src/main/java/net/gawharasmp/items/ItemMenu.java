package net.gawharasmp.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public final class ItemMenu {
    private static final int[] ITEM_SLOTS = {10, 12, 14, 16, 19, 21, 23, 25, 28, 30};

    private ItemMenu() {
    }

    public static void open(Player player, ItemFactory itemFactory) {
        ItemMenuHolder holder = new ItemMenuHolder();
        Inventory inventory = Bukkit.createInventory(holder, 54,
                ChatColor.DARK_AQUA + "GawharaItems");
        holder.setInventory(inventory);

        ItemStack filler = namedItem(Material.GRAY_STAINED_GLASS_PANE, " ", List.of());
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if (slot < 9 || slot >= 45 || slot % 9 == 0 || slot % 9 == 8) {
                inventory.setItem(slot, filler);
            }
        }

        ItemType[] types = ItemType.values();
        for (int i = 0; i < types.length && i < ITEM_SLOTS.length; i++) {
            inventory.setItem(ITEM_SLOTS[i], itemFactory.create(types[i]));
        }

        ItemStack info = namedItem(Material.BOOK, ChatColor.GOLD + "Item Showcase", List.of(
                ChatColor.GRAY + "Browse the custom GawharaSMP items.",
                ChatColor.YELLOW + "Click an item to read its ability.",
                ChatColor.YELLOW + "Ask an admin how to obtain it."
        ));
        inventory.setItem(49, info);
        player.openInventory(inventory);
    }

    private static ItemStack namedItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (!lore.isEmpty()) {
                meta.setLore(lore);
            }
            item.setItemMeta(meta);
        }
        return item;
    }
}
