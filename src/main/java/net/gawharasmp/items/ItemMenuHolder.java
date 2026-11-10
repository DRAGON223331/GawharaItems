package net.gawharasmp.items;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public final class ItemMenuHolder implements InventoryHolder {
    private Inventory inventory;
    private final int page;
    public ItemMenuHolder(int page) { this.page = Math.max(0, page); }
    public int getPage() { return page; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    @Override public Inventory getInventory() { return inventory; }
}
