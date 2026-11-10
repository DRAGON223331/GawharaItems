package net.gawharasmp.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public final class ItemMenu {
    private static final int[] ITEM_SLOTS = {
            10, 11, 12, 13, 14, 15, 16,
            19, 20, 21, 22, 23, 24, 25,
            28, 29, 30, 31, 32, 33, 34,
            37, 38, 39, 40, 41, 42, 43
    };
    private ItemMenu() { }

    public static void open(Player player, ItemFactory itemFactory) { open(player, itemFactory, 0); }

    public static void open(Player player, ItemFactory itemFactory, int requestedPage) {
        ItemType[] all = ItemType.values();
        int pageCount = Math.max(1, (int) Math.ceil(all.length / (double) ITEM_SLOTS.length));
        int page = Math.max(0, Math.min(requestedPage, pageCount - 1));
        ItemMenuHolder holder = new ItemMenuHolder(page);
        Inventory inventory = Bukkit.createInventory(holder, 54,
                ChatColor.DARK_AQUA + "GawharaItems " + ChatColor.GRAY + "- " + (page + 1) + "/" + pageCount);
        holder.setInventory(inventory);

        ItemStack filler = namedItem(Material.GRAY_STAINED_GLASS_PANE, " ", List.of());
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if (slot < 9 || slot >= 45 || slot % 9 == 0 || slot % 9 == 8) inventory.setItem(slot, filler);
        }
        int start = page * ITEM_SLOTS.length;
        for (int i = 0; i < ITEM_SLOTS.length && start + i < all.length; i++) {
            inventory.setItem(ITEM_SLOTS[i], itemFactory.create(all[start + i]));
        }
        if (page > 0) inventory.setItem(45, namedItem(Material.ARROW, ChatColor.YELLOW + "Previous page", List.of(ChatColor.GRAY + "Go to page " + page)));
        inventory.setItem(49, namedItem(Material.BOOK, ChatColor.GOLD + "GawharaSMP Item Catalogue", List.of(
                ChatColor.GRAY + "Browse custom weapons, armor and tools.",
                ChatColor.YELLOW + "Click an item to read its ability.",
                ChatColor.YELLOW + "Page " + (page + 1) + " of " + pageCount
        )));
        if (page < pageCount - 1) inventory.setItem(53, namedItem(Material.ARROW, ChatColor.YELLOW + "Next page", List.of(ChatColor.GRAY + "Go to page " + (page + 2))));
        player.openInventory(inventory);
    }

    private static ItemStack namedItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (!lore.isEmpty()) meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }
}
