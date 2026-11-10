package net.gawharasmp.items;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public final class MenuListener implements Listener {
    private final ItemFactory itemFactory;
    public MenuListener(ItemFactory itemFactory) { this.itemFactory = itemFactory; }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof ItemMenuHolder holder)) return;
        event.setCancelled(true);
        int rawSlot = event.getRawSlot();
        if (rawSlot < 0 || rawSlot >= event.getView().getTopInventory().getSize()) return;
        if (event.getWhoClicked() instanceof Player player) {
            if (rawSlot == 45 && holder.getPage() > 0) {
                ItemMenu.open(player, itemFactory, holder.getPage() - 1);
                return;
            }
            if (rawSlot == 53) {
                ItemMenu.open(player, itemFactory, holder.getPage() + 1);
                return;
            }
        }
        ItemStack clicked = event.getCurrentItem();
        ItemType type = itemFactory.getType(clicked);
        if (type != null) event.getWhoClicked().sendMessage(ChatColor.AQUA + type.getDisplayName()
                + ChatColor.GRAY + ": " + type.getAbility());
    }

    @EventHandler
    public void onMenuDrag(InventoryDragEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof ItemMenuHolder)) return;
        int topSize = event.getView().getTopInventory().getSize();
        if (event.getRawSlots().stream().anyMatch(slot -> slot < topSize)) event.setCancelled(true);
    }
}
