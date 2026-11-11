package net.gawharasmp.items;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class ItemFactory {
    private final JavaPlugin plugin;
    private final NamespacedKey itemIdKey;

    public ItemFactory(JavaPlugin plugin) {
        this.plugin = plugin;
        this.itemIdKey = new NamespacedKey(plugin, "custom_item_id");
    }

    public ItemStack create(ItemType type) {
        ItemStack stack = new ItemStack(type.getMaterial());
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) return stack;

        meta.setDisplayName(type.getColor() + "§l" + type.getDisplayName());
        meta.setLore(List.of(
                "§7" + type.getSummary(),
                "§8",
                "§eAbility: §f" + type.getAbility(),
                "§8GawharaSMP Custom Item",
                "§0ID: " + type.getId()
        ));
        meta.getPersistentDataContainer().set(itemIdKey, PersistentDataType.STRING, type.getId());

        // Java 1.21.4+ item_model component; the matching definitions live in the included pack.
        if (type.getModelId() != null) {
            meta.setItemModel(new NamespacedKey(plugin, type.getModelId()));
        }

        // Custom equipment assets control the worn appearance of all nine armor sets.
        if (type.isArmorPiece()) {
            EquippableComponent equippable = meta.getEquippable();
            equippable.setModel(new NamespacedKey(plugin, type.getArmorSet()));
            equippable.setSlot(slotFor(type.getArmorPiece()));
            equippable.setEquipOnInteract(true);
            equippable.setSwappable(true);
            meta.setEquippable(equippable);
        }

        applyEnchantments(meta, type);
        stack.setItemMeta(meta);
        return stack;
    }

    public ItemType getType(ItemStack stack) {
        if (stack == null || stack.getType().isAir() || !stack.hasItemMeta()) return null;
        String id = stack.getItemMeta().getPersistentDataContainer()
                .get(itemIdKey, PersistentDataType.STRING);
        return ItemType.fromId(id);
    }

    private EquipmentSlot slotFor(String piece) {
        return switch (piece) {
            case "helmet" -> EquipmentSlot.HEAD;
            case "chestplate" -> EquipmentSlot.CHEST;
            case "leggings" -> EquipmentSlot.LEGS;
            case "boots" -> EquipmentSlot.FEET;
            default -> throw new IllegalArgumentException("Unknown armor piece: " + piece);
        };
    }

    private void applyEnchantments(ItemMeta meta, ItemType type) {
        switch (type) {
            case MINER_PICKAXE, SHADOW_TOOL, ROYAL_TOOL, DRAGON_TOOL, DEMON_TOOL,
                 ICE_TOOL, ANGEL_TOOL, WARRIOR_TOOL, VIKING_TOOL, GLADIATOR_TOOL -> {
                enchant(meta, "efficiency", 5); enchant(meta, "unbreaking", 3); enchant(meta, "fortune", 3);
            }
            case LUMBER_AXE -> {
                enchant(meta, "efficiency", 5); enchant(meta, "unbreaking", 3);
            }
            case FLAME_BLADE, DRAGON_BLADE, DRAGON_WEAPON -> {
                enchant(meta, "sharpness", 5); enchant(meta, "fire_aspect", 2); enchant(meta, "unbreaking", 3);
            }
            case SHADOW_WEAPON, ROYAL_WEAPON, DEMON_WEAPON, WARRIOR_WEAPON,
                 VIKING_WEAPON, GLADIATOR_WEAPON, WAR_HAMMER -> {
                enchant(meta, "sharpness", 5); enchant(meta, "unbreaking", 3);
            }
            case ICE_WEAPON, ANGEL_WEAPON, BATTLE_SPEAR -> {
                enchant(meta, "impaling", 5); enchant(meta, "unbreaking", 3);
            }
            case HUNTER_CROSSBOW -> {
                enchant(meta, "quick_charge", 3); enchant(meta, "piercing", 4); enchant(meta, "unbreaking", 3);
            }
            case FROST_BLADE, LIGHTNING_BLADE, SHADOW_BLADE, VENOM_BLADE,
                 VITAL_BLADE, SPIRIT_BLADE -> {
                enchant(meta, "sharpness", 5); enchant(meta, "unbreaking", 3);
            }
            case GUARDIAN_HELMET, GUARDIAN_CHESTPLATE, GUARDIAN_LEGGINGS, GUARDIAN_BOOTS,
                 SHADOW_HELMET, SHADOW_CHESTPLATE, SHADOW_LEGGINGS, SHADOW_BOOTS,
                 ROYAL_HELMET, ROYAL_CHESTPLATE, ROYAL_LEGGINGS, ROYAL_BOOTS,
                 DRAGON_HELMET, DRAGON_CHESTPLATE, DRAGON_LEGGINGS, DRAGON_BOOTS,
                 DEMON_HELMET, DEMON_CHESTPLATE, DEMON_LEGGINGS, DEMON_BOOTS,
                 ICE_HELMET, ICE_CHESTPLATE, ICE_LEGGINGS, ICE_BOOTS,
                 ANGEL_HELMET, ANGEL_CHESTPLATE, ANGEL_LEGGINGS, ANGEL_BOOTS,
                 WARRIOR_HELMET, WARRIOR_CHESTPLATE, WARRIOR_LEGGINGS, WARRIOR_BOOTS,
                 VIKING_HELMET, VIKING_CHESTPLATE, VIKING_LEGGINGS, VIKING_BOOTS,
                 GLADIATOR_HELMET, GLADIATOR_CHESTPLATE, GLADIATOR_LEGGINGS, GLADIATOR_BOOTS -> {
                enchant(meta, "protection", 4); enchant(meta, "unbreaking", 3);
            }
            default -> { /* Wands use their abilities instead of combat enchantments. */ }
        }
    }

    private void enchant(ItemMeta meta, String key, int level) {
        Enchantment enchantment = Registry.ENCHANTMENT.get(NamespacedKey.minecraft(key));
        if (enchantment != null) meta.addEnchant(enchantment, level, true);
    }
}
