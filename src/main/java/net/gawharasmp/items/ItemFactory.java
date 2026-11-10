package net.gawharasmp.items;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class ItemFactory {
    private final NamespacedKey itemIdKey;

    public ItemFactory(JavaPlugin plugin) {
        this.itemIdKey = new NamespacedKey(plugin, "custom_item_id");
    }

    public ItemStack create(ItemType type) {
        ItemStack stack = new ItemStack(type.getMaterial());
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) {
            return stack;
        }

        meta.setDisplayName(type.getColor() + "§l" + type.getDisplayName());
        meta.setLore(List.of(
                "§7" + type.getSummary(),
                "§8",
                "§eAbility: §f" + type.getAbility(),
                "§8GawharaSMP Custom Item",
                "§0ID: " + type.getId()
        ));
        meta.getPersistentDataContainer().set(itemIdKey, PersistentDataType.STRING, type.getId());
        applyEnchantments(meta, type);
        stack.setItemMeta(meta);
        return stack;
    }

    public ItemType getType(ItemStack stack) {
        if (stack == null || stack.getType().isAir() || !stack.hasItemMeta()) {
            return null;
        }

        String id = stack.getItemMeta()
                .getPersistentDataContainer()
                .get(itemIdKey, PersistentDataType.STRING);
        return ItemType.fromId(id);
    }

    private void applyEnchantments(ItemMeta meta, ItemType type) {
        switch (type) {
            case MINER_PICKAXE -> {
                enchant(meta, "efficiency", 5);
                enchant(meta, "unbreaking", 3);
                enchant(meta, "fortune", 2);
            }
            case LUMBER_AXE -> {
                enchant(meta, "efficiency", 4);
                enchant(meta, "unbreaking", 3);
            }
            case FLAME_BLADE -> {
                enchant(meta, "sharpness", 4);
                enchant(meta, "fire_aspect", 2);
                enchant(meta, "unbreaking", 3);
            }
            case FROST_BLADE -> {
                enchant(meta, "sharpness", 4);
                enchant(meta, "unbreaking", 3);
            }
            case GUARDIAN_HELMET, GUARDIAN_CHESTPLATE,
                 GUARDIAN_LEGGINGS, GUARDIAN_BOOTS -> {
                enchant(meta, "protection", 3);
                enchant(meta, "unbreaking", 3);
            }
            default -> {
                // Wands use their abilities instead of combat enchantments.
            }
        }
    }

    private void enchant(ItemMeta meta, String key, int level) {
        Enchantment enchantment = Registry.ENCHANTMENT.get(NamespacedKey.minecraft(key));
        if (enchantment != null) {
            meta.addEnchant(enchantment, level, true);
        }
    }
}
