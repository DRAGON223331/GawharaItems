package net.gawharasmp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;

public enum ItemType {
    MINER_PICKAXE("miner_pickaxe", "Miner's Pickaxe", Material.DIAMOND_PICKAXE, ChatColor.AQUA,
            "A specialist mining pickaxe.", "Grants a short Haste boost when you mine ore.", null, null, null),
    LUMBER_AXE("lumber_axe", "Lumberjack's Axe", Material.DIAMOND_AXE, ChatColor.GREEN,
            "A reinforced woodcutting axe.", "Grants a short Haste boost when you chop logs.", null, null, null),

    FLAME_BLADE("flame_blade", "Flame Blade", Material.NETHERITE_SWORD, ChatColor.GOLD,
            "A blade forged in fire.", "Right-click to burn and damage nearby monsters.", "flame_blade", null, null),
    FROST_BLADE("frost_blade", "Frost Blade", Material.DIAMOND_SWORD, ChatColor.AQUA,
            "A blade chilled by ancient ice.", "Right-click to damage and slow nearby monsters.", "frost_blade", null, null),
    LIGHTNING_BLADE("lightning_blade", "Stormbreaker", Material.NETHERITE_SWORD, ChatColor.YELLOW,
            "A sword charged with storm energy.", "Right-click to call a lightning burst at the block you target.", "lightning_blade", null, null),
    SHADOW_BLADE("shadow_blade", "Umbral Edge", Material.NETHERITE_SWORD, ChatColor.DARK_PURPLE,
            "A blade carved from living shadow.", "Right-click to blind and weaken nearby monsters.", "shadow_blade", null, null),
    VENOM_BLADE("venom_blade", "Venom Fang", Material.NETHERITE_SWORD, ChatColor.DARK_GREEN,
            "A toxic blade coated in ancient venom.", "Right-click to poison and damage nearby monsters.", "venom_blade", null, null),
    VITAL_BLADE("vital_blade", "Lifebinder", Material.NETHERITE_SWORD, ChatColor.LIGHT_PURPLE,
            "A living blade that feeds on battle.", "Right-click to damage nearby monsters and restore your health.", "vital_blade", null, null),
    DRAGON_BLADE("dragon_blade", "Wyrmfang", Material.NETHERITE_SWORD, ChatColor.RED,
            "A heavy blade tempered in dragonfire.", "Right-click to unleash a fiery shockwave.", "dragon_blade", null, null),
    SPIRIT_BLADE("spirit_blade", "Soulreaper", Material.NETHERITE_SWORD, ChatColor.WHITE,
            "A spectral blade between worlds.", "Right-click to mark nearby monsters and empower yourself.", "spirit_blade", null, null),

    LIGHTNING_WAND("lightning_wand", "Lightning Wand", Material.BLAZE_ROD, ChatColor.YELLOW,
            "A wand charged with storm energy.", "Right-click to call lightning on your target.", null, null, null),
    HEALING_WAND("healing_wand", "Healing Wand", Material.STICK, ChatColor.LIGHT_PURPLE,
            "A gentle wand of restoration.", "Right-click to restore health.", null, null, null),

    GUARDIAN_HELMET("guardian_helmet", "Guardian Helmet", Material.NETHERITE_HELMET, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),
    GUARDIAN_CHESTPLATE("guardian_chestplate", "Guardian Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),
    GUARDIAN_LEGGINGS("guardian_leggings", "Guardian Leggings", Material.NETHERITE_LEGGINGS, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),
    GUARDIAN_BOOTS("guardian_boots", "Guardian Boots", Material.NETHERITE_BOOTS, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),

    SHADOW_HELMET("shadow_helmet", "Shadow Helmet", Material.NETHERITE_HELMET, ChatColor.DARK_PURPLE,
            "Shadow armor: the full set grants stealth and speed.", "Full set: Speed II and invisibility while sneaking.", "shadow_helmet", "shadow", "helmet"),
    SHADOW_CHESTPLATE("shadow_chestplate", "Shadow Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.DARK_PURPLE,
            "Shadow armor: the full set grants stealth and speed.", "Full set: Speed II and invisibility while sneaking.", "shadow_chestplate", "shadow", "chestplate"),
    SHADOW_LEGGINGS("shadow_leggings", "Shadow Leggings", Material.NETHERITE_LEGGINGS, ChatColor.DARK_PURPLE,
            "Shadow armor: the full set grants stealth and speed.", "Full set: Speed II and invisibility while sneaking.", "shadow_leggings", "shadow", "leggings"),
    SHADOW_BOOTS("shadow_boots", "Shadow Boots", Material.NETHERITE_BOOTS, ChatColor.DARK_PURPLE,
            "Shadow armor: the full set grants stealth and speed.", "Full set: Speed II and invisibility while sneaking.", "shadow_boots", "shadow", "boots"),

    ROYAL_HELMET("royal_helmet", "Royal Helmet", Material.NETHERITE_HELMET, ChatColor.GOLD,
            "Royal armor: a balanced defensive set.", "Full set: Resistance and Regeneration.", "royal_helmet", "royal", "helmet"),
    ROYAL_CHESTPLATE("royal_chestplate", "Royal Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.GOLD,
            "Royal armor: a balanced defensive set.", "Full set: Resistance and Regeneration.", "royal_chestplate", "royal", "chestplate"),
    ROYAL_LEGGINGS("royal_leggings", "Royal Leggings", Material.NETHERITE_LEGGINGS, ChatColor.GOLD,
            "Royal armor: a balanced defensive set.", "Full set: Resistance and Regeneration.", "royal_leggings", "royal", "leggings"),
    ROYAL_BOOTS("royal_boots", "Royal Boots", Material.NETHERITE_BOOTS, ChatColor.GOLD,
            "Royal armor: a balanced defensive set.", "Full set: Resistance and Regeneration.", "royal_boots", "royal", "boots"),

    DRAGON_HELMET("dragon_helmet", "Dragon Helmet", Material.NETHERITE_HELMET, ChatColor.RED,
            "Dragon armor: built for fire and aggression.", "Full set: Fire Resistance and Strength.", "dragon_helmet", "dragon", "helmet"),
    DRAGON_CHESTPLATE("dragon_chestplate", "Dragon Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.RED,
            "Dragon armor: built for fire and aggression.", "Full set: Fire Resistance and Strength.", "dragon_chestplate", "dragon", "chestplate"),
    DRAGON_LEGGINGS("dragon_leggings", "Dragon Leggings", Material.NETHERITE_LEGGINGS, ChatColor.RED,
            "Dragon armor: built for fire and aggression.", "Full set: Fire Resistance and Strength.", "dragon_leggings", "dragon", "leggings"),
    DRAGON_BOOTS("dragon_boots", "Dragon Boots", Material.NETHERITE_BOOTS, ChatColor.RED,
            "Dragon armor: built for fire and aggression.", "Full set: Fire Resistance and Strength.", "dragon_boots", "dragon", "boots"),

    DEMON_HELMET("demon_helmet", "Demon Helmet", Material.NETHERITE_HELMET, ChatColor.DARK_RED,
            "Demon armor: an aggressive combat set.", "Full set: Strength II and Speed.", "demon_helmet", "demon", "helmet"),
    DEMON_CHESTPLATE("demon_chestplate", "Demon Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.DARK_RED,
            "Demon armor: an aggressive combat set.", "Full set: Strength II and Speed.", "demon_chestplate", "demon", "chestplate"),
    DEMON_LEGGINGS("demon_leggings", "Demon Leggings", Material.NETHERITE_LEGGINGS, ChatColor.DARK_RED,
            "Demon armor: an aggressive combat set.", "Full set: Strength II and Speed.", "demon_leggings", "demon", "leggings"),
    DEMON_BOOTS("demon_boots", "Demon Boots", Material.NETHERITE_BOOTS, ChatColor.DARK_RED,
            "Demon armor: an aggressive combat set.", "Full set: Strength II and Speed.", "demon_boots", "demon", "boots"),

    ICE_HELMET("ice_helmet", "Ice Helmet", Material.NETHERITE_HELMET, ChatColor.AQUA,
            "Ice armor: resilient and quick in frozen terrain.", "Full set: Resistance and Speed; attackers are slowed.", "ice_helmet", "ice", "helmet"),
    ICE_CHESTPLATE("ice_chestplate", "Ice Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.AQUA,
            "Ice armor: resilient and quick in frozen terrain.", "Full set: Resistance and Speed; attackers are slowed.", "ice_chestplate", "ice", "chestplate"),
    ICE_LEGGINGS("ice_leggings", "Ice Leggings", Material.NETHERITE_LEGGINGS, ChatColor.AQUA,
            "Ice armor: resilient and quick in frozen terrain.", "Full set: Resistance and Speed; attackers are slowed.", "ice_leggings", "ice", "leggings"),
    ICE_BOOTS("ice_boots", "Ice Boots", Material.NETHERITE_BOOTS, ChatColor.AQUA,
            "Ice armor: resilient and quick in frozen terrain.", "Full set: Resistance and Speed; attackers are slowed.", "ice_boots", "ice", "boots"),

    ANGEL_HELMET("angel_helmet", "Angel Helmet", Material.NETHERITE_HELMET, ChatColor.WHITE,
            "Angel armor: mobility and protection from falls.", "Full set: Slow Falling and Jump Boost II.", "angel_helmet", "angel", "helmet"),
    ANGEL_CHESTPLATE("angel_chestplate", "Angel Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.WHITE,
            "Angel armor: mobility and protection from falls.", "Full set: Slow Falling and Jump Boost II.", "angel_chestplate", "angel", "chestplate"),
    ANGEL_LEGGINGS("angel_leggings", "Angel Leggings", Material.NETHERITE_LEGGINGS, ChatColor.WHITE,
            "Angel armor: mobility and protection from falls.", "Full set: Slow Falling and Jump Boost II.", "angel_leggings", "angel", "leggings"),
    ANGEL_BOOTS("angel_boots", "Angel Boots", Material.NETHERITE_BOOTS, ChatColor.WHITE,
            "Angel armor: mobility and protection from falls.", "Full set: Slow Falling and Jump Boost II.", "angel_boots", "angel", "boots");

    private final String id;
    private final String displayName;
    private final Material material;
    private final ChatColor color;
    private final String summary;
    private final String ability;
    private final String modelId;
    private final String armorSet;
    private final String armorPiece;

    ItemType(String id, String displayName, Material material, ChatColor color,
             String summary, String ability, String modelId, String armorSet, String armorPiece) {
        this.id = id;
        this.displayName = displayName;
        this.material = material;
        this.color = color;
        this.summary = summary;
        this.ability = ability;
        this.modelId = modelId;
        this.armorSet = armorSet;
        this.armorPiece = armorPiece;
    }

    public String getId() { return id; }
    public String getDisplayName() { return displayName; }
    public Material getMaterial() { return material; }
    public ChatColor getColor() { return color; }
    public String getSummary() { return summary; }
    public String getAbility() { return ability; }
    public String getModelId() { return modelId; }
    public String getArmorSet() { return armorSet; }
    public String getArmorPiece() { return armorPiece; }
    public boolean isArmorPiece() { return armorSet != null; }

    public boolean isAbilitySword() {
        return switch (this) {
            case FLAME_BLADE, FROST_BLADE, LIGHTNING_BLADE, SHADOW_BLADE,
                 VENOM_BLADE, VITAL_BLADE, DRAGON_BLADE, SPIRIT_BLADE -> true;
            default -> false;
        };
    }

    public static ItemType fromId(String id) {
        if (id == null || id.isBlank()) return null;
        return Arrays.stream(values())
                .filter(type -> type.id.equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }
}
