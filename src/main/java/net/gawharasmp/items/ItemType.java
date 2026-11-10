package net.gawharasmp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;

public enum ItemType {
    MINER_PICKAXE(
            "miner_pickaxe", "Miner's Pickaxe", Material.DIAMOND_PICKAXE,
            ChatColor.AQUA, "A specialist mining pickaxe.", "Grants a short Haste boost when you mine ore."
    ),
    LUMBER_AXE(
            "lumber_axe", "Lumberjack's Axe", Material.DIAMOND_AXE,
            ChatColor.GREEN, "A reinforced woodcutting axe.", "Grants a short Haste boost when you chop logs."
    ),
    FLAME_BLADE(
            "flame_blade", "Flame Blade", Material.NETHERITE_SWORD,
            ChatColor.GOLD, "A blade forged in fire.", "Right-click to burn and damage nearby monsters."
    ),
    FROST_BLADE(
            "frost_blade", "Frost Blade", Material.DIAMOND_SWORD,
            ChatColor.AQUA, "A blade chilled by ancient ice.", "Right-click to damage and slow nearby monsters."
    ),
    LIGHTNING_WAND(
            "lightning_wand", "Lightning Wand", Material.BLAZE_ROD,
            ChatColor.YELLOW, "A wand charged with storm energy.", "Right-click to call lightning on your target."
    ),
    HEALING_WAND(
            "healing_wand", "Healing Wand", Material.STICK,
            ChatColor.LIGHT_PURPLE, "A gentle wand of restoration.", "Right-click to restore health."
    ),
    GUARDIAN_HELMET(
            "guardian_helmet", "Guardian Helmet", Material.NETHERITE_HELMET,
            ChatColor.BLUE, "Part of the Guardian armor set.", "Reduces incoming damage while equipped."
    ),
    GUARDIAN_CHESTPLATE(
            "guardian_chestplate", "Guardian Chestplate", Material.NETHERITE_CHESTPLATE,
            ChatColor.BLUE, "Part of the Guardian armor set.", "Reduces incoming damage while equipped."
    ),
    GUARDIAN_LEGGINGS(
            "guardian_leggings", "Guardian Leggings", Material.NETHERITE_LEGGINGS,
            ChatColor.BLUE, "Part of the Guardian armor set.", "Reduces incoming damage while equipped."
    ),
    GUARDIAN_BOOTS(
            "guardian_boots", "Guardian Boots", Material.NETHERITE_BOOTS,
            ChatColor.BLUE, "Part of the Guardian armor set.", "Reduces incoming damage while equipped."
    );

    private final String id;
    private final String displayName;
    private final Material material;
    private final ChatColor color;
    private final String summary;
    private final String ability;

    ItemType(String id, String displayName, Material material, ChatColor color,
             String summary, String ability) {
        this.id = id;
        this.displayName = displayName;
        this.material = material;
        this.color = color;
        this.summary = summary;
        this.ability = ability;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getMaterial() {
        return material;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getSummary() {
        return summary;
    }

    public String getAbility() {
        return ability;
    }

    public static ItemType fromId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return Arrays.stream(values())
                .filter(type -> type.id.equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
}
