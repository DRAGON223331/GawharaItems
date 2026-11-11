package net.gawharasmp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;

public enum ItemType {
    // Custom tools
    MINER_PICKAXE("miner_pickaxe", "Miner's War Pickaxe", Material.DIAMOND_PICKAXE, ChatColor.AQUA,
            "A heavy mining pick with a reinforced steel head.", "Grants a short Haste boost when you mine ore.", "miner_pickaxe", null, null),
    LUMBER_AXE("lumber_axe", "Lumber War Axe", Material.DIAMOND_AXE, ChatColor.GREEN,
            "A broad-headed woodcutter's axe with a wrapped haft.", "Grants a short Haste boost when you chop logs.", "lumber_axe", null, null),

    // Elemental weapons
    FLAME_BLADE("flame_blade", "Flame Blade", Material.NETHERITE_SWORD, ChatColor.GOLD,
            "A battle-forged blade with a fiery edge.", "Right-click to burn and damage nearby monsters.", "flame_blade", null, null),
    FROST_BLADE("frost_blade", "Frost Blade", Material.DIAMOND_SWORD, ChatColor.AQUA,
            "A cold-forged sword with a bright steel edge.", "Right-click to damage and slow nearby monsters.", "frost_blade", null, null),
    LIGHTNING_BLADE("lightning_blade", "Stormbreaker", Material.NETHERITE_SWORD, ChatColor.YELLOW,
            "A storm-marked sword forged for close combat.", "Right-click to call a lightning burst at the block you target.", "lightning_blade", null, null),
    SHADOW_BLADE("shadow_blade", "Umbral Edge", Material.NETHERITE_SWORD, ChatColor.DARK_PURPLE,
            "A dark steel blade made for swift strikes.", "Right-click to blind and weaken nearby monsters.", "shadow_blade", null, null),
    VENOM_BLADE("venom_blade", "Venom Fang", Material.NETHERITE_SWORD, ChatColor.DARK_GREEN,
            "A green-black blade coated in ancient venom.", "Right-click to poison and damage nearby monsters.", "venom_blade", null, null),
    VITAL_BLADE("vital_blade", "Lifebinder", Material.NETHERITE_SWORD, ChatColor.LIGHT_PURPLE,
            "A silver sword bearing a life-giving crest.", "Right-click to damage nearby monsters and restore your health.", "vital_blade", null, null),
    DRAGON_BLADE("dragon_blade", "Wyrmfang", Material.NETHERITE_SWORD, ChatColor.RED,
            "A heavy dragon-forged blade with a curved edge.", "Right-click to unleash a fiery shockwave.", "dragon_blade", null, null),
    SPIRIT_BLADE("spirit_blade", "Soulreaper", Material.NETHERITE_SWORD, ChatColor.WHITE,
            "A pale steel sword marked with an old rune.", "Right-click to mark nearby monsters and empower yourself.", "spirit_blade", null, null),

    LIGHTNING_WAND("lightning_wand", "Lightning Wand", Material.BLAZE_ROD, ChatColor.YELLOW,
            "A wand charged with storm energy.", "Right-click to call lightning on your target.", null, null, null),
    HEALING_WAND("healing_wand", "Healing Wand", Material.STICK, ChatColor.LIGHT_PURPLE,
            "A gentle wand of restoration.", "Right-click to restore health.", null, null, null),

    // Legacy Guardian items kept for compatibility with existing inventories.
    GUARDIAN_HELMET("guardian_helmet", "Guardian Helmet", Material.NETHERITE_HELMET, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),
    GUARDIAN_CHESTPLATE("guardian_chestplate", "Guardian Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),
    GUARDIAN_LEGGINGS("guardian_leggings", "Guardian Leggings", Material.NETHERITE_LEGGINGS, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),
    GUARDIAN_BOOTS("guardian_boots", "Guardian Boots", Material.NETHERITE_BOOTS, ChatColor.BLUE,
            "Part of the legacy Guardian armor set.", "Reduces incoming damage while equipped.", null, null, null),

    // Original six themed armor bundles.
    SHADOW_HELMET("shadow_helmet", "Shadow Helmet", Material.NETHERITE_HELMET, ChatColor.DARK_PURPLE,
            "A dark steel helm with a hooded silhouette.", "Full set: Speed II and invisibility while sneaking.", "shadow_helmet", "shadow", "helmet"),
    SHADOW_CHESTPLATE("shadow_chestplate", "Shadow Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.DARK_PURPLE,
            "Layered dark steel plates with a shadow crest.", "Full set: Speed II and invisibility while sneaking.", "shadow_chestplate", "shadow", "chestplate"),
    SHADOW_LEGGINGS("shadow_leggings", "Shadow Leggings", Material.NETHERITE_LEGGINGS, ChatColor.DARK_PURPLE,
            "Reinforced leggings with dark metal knee plates.", "Full set: Speed II and invisibility while sneaking.", "shadow_leggings", "shadow", "leggings"),
    SHADOW_BOOTS("shadow_boots", "Shadow Boots", Material.NETHERITE_BOOTS, ChatColor.DARK_PURPLE,
            "Quiet boots with reinforced greaves.", "Full set: Speed II and invisibility while sneaking.", "shadow_boots", "shadow", "boots"),

    ROYAL_HELMET("royal_helmet", "Royal Helmet", Material.NETHERITE_HELMET, ChatColor.GOLD,
            "A decorated steel helm bearing a royal crest.", "Full set: Resistance and Regeneration.", "royal_helmet", "royal", "helmet"),
    ROYAL_CHESTPLATE("royal_chestplate", "Royal Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.GOLD,
            "A polished breastplate with brass trim.", "Full set: Resistance and Regeneration.", "royal_chestplate", "royal", "chestplate"),
    ROYAL_LEGGINGS("royal_leggings", "Royal Leggings", Material.NETHERITE_LEGGINGS, ChatColor.GOLD,
            "Royal plated leggings with reinforced knees.", "Full set: Resistance and Regeneration.", "royal_leggings", "royal", "leggings"),
    ROYAL_BOOTS("royal_boots", "Royal Boots", Material.NETHERITE_BOOTS, ChatColor.GOLD,
            "Royal greaves with metal toe guards.", "Full set: Resistance and Regeneration.", "royal_boots", "royal", "boots"),

    DRAGON_HELMET("dragon_helmet", "Dragon Helmet", Material.NETHERITE_HELMET, ChatColor.RED,
            "A dragon-crested battle helm with a heavy brow.", "Full set: Fire Resistance and Strength.", "dragon_helmet", "dragon", "helmet"),
    DRAGON_CHESTPLATE("dragon_chestplate", "Dragon Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.RED,
            "Dark red plate armor with a dragon crest.", "Full set: Fire Resistance and Strength.", "dragon_chestplate", "dragon", "chestplate"),
    DRAGON_LEGGINGS("dragon_leggings", "Dragon Leggings", Material.NETHERITE_LEGGINGS, ChatColor.RED,
            "Dragon-forged leg plates with heavy knees.", "Full set: Fire Resistance and Strength.", "dragon_leggings", "dragon", "leggings"),
    DRAGON_BOOTS("dragon_boots", "Dragon Boots", Material.NETHERITE_BOOTS, ChatColor.RED,
            "Heavy greaves forged in dragonfire.", "Full set: Fire Resistance and Strength.", "dragon_boots", "dragon", "boots"),

    DEMON_HELMET("demon_helmet", "Demon Helmet", Material.NETHERITE_HELMET, ChatColor.DARK_RED,
            "A horned iron helm built for the front line.", "Full set: Strength II and Speed.", "demon_helmet", "demon", "helmet"),
    DEMON_CHESTPLATE("demon_chestplate", "Demon Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.DARK_RED,
            "Dark crimson plate armor with a brutal crest.", "Full set: Strength II and Speed.", "demon_chestplate", "demon", "chestplate"),
    DEMON_LEGGINGS("demon_leggings", "Demon Leggings", Material.NETHERITE_LEGGINGS, ChatColor.DARK_RED,
            "Heavy dark leg plates with reinforced knees.", "Full set: Strength II and Speed.", "demon_leggings", "demon", "leggings"),
    DEMON_BOOTS("demon_boots", "Demon Boots", Material.NETHERITE_BOOTS, ChatColor.DARK_RED,
            "Dark greaves with red metal edging.", "Full set: Strength II and Speed.", "demon_boots", "demon", "boots"),

    ICE_HELMET("ice_helmet", "Ice Helmet", Material.NETHERITE_HELMET, ChatColor.AQUA,
            "A pale steel helm with a frozen crest.", "Full set: Resistance and Speed; attackers are slowed.", "ice_helmet", "ice", "helmet"),
    ICE_CHESTPLATE("ice_chestplate", "Ice Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.AQUA,
            "Cold steel plate armor with a snowflake crest.", "Full set: Resistance and Speed; attackers are slowed.", "ice_chestplate", "ice", "chestplate"),
    ICE_LEGGINGS("ice_leggings", "Ice Leggings", Material.NETHERITE_LEGGINGS, ChatColor.AQUA,
            "Frost-forged leg armor with plated knees.", "Full set: Resistance and Speed; attackers are slowed.", "ice_leggings", "ice", "leggings"),
    ICE_BOOTS("ice_boots", "Ice Boots", Material.NETHERITE_BOOTS, ChatColor.AQUA,
            "Steel boots with pale ice-blue guards.", "Full set: Resistance and Speed; attackers are slowed.", "ice_boots", "ice", "boots"),

    ANGEL_HELMET("angel_helmet", "Angel Helmet", Material.NETHERITE_HELMET, ChatColor.WHITE,
            "An ivory helm with wing-shaped side guards.", "Full set: Slow Falling and Jump Boost II.", "angel_helmet", "angel", "helmet"),
    ANGEL_CHESTPLATE("angel_chestplate", "Angel Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.WHITE,
            "Ivory and gold plate armor with a winged crest.", "Full set: Slow Falling and Jump Boost II.", "angel_chestplate", "angel", "chestplate"),
    ANGEL_LEGGINGS("angel_leggings", "Angel Leggings", Material.NETHERITE_LEGGINGS, ChatColor.WHITE,
            "Ivory leg plates with gold knee guards.", "Full set: Slow Falling and Jump Boost II.", "angel_leggings", "angel", "leggings"),
    ANGEL_BOOTS("angel_boots", "Angel Boots", Material.NETHERITE_BOOTS, ChatColor.WHITE,
            "Ivory greaves with gold toe plates.", "Full set: Slow Falling and Jump Boost II.", "angel_boots", "angel", "boots"),

    // New medieval war bundles.
    WARRIOR_HELMET("warrior_helmet", "Warrior Helmet", Material.NETHERITE_HELMET, ChatColor.GRAY,
            "A practical steel helmet with a bronze crest and face guard.", "Full set: Resistance and Strength.", "warrior_helmet", "warrior", "helmet"),
    WARRIOR_CHESTPLATE("warrior_chestplate", "Warrior Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.GRAY,
            "Traditional steel breastplate with bronze rivets.", "Full set: Resistance and Strength.", "warrior_chestplate", "warrior", "chestplate"),
    WARRIOR_LEGGINGS("warrior_leggings", "Warrior Leggings", Material.NETHERITE_LEGGINGS, ChatColor.GRAY,
            "Steel leg guards with reinforced knee plates.", "Full set: Resistance and Strength.", "warrior_leggings", "warrior", "leggings"),
    WARRIOR_BOOTS("warrior_boots", "Warrior Boots", Material.NETHERITE_BOOTS, ChatColor.GRAY,
            "Heavy steel greaves made for battle.", "Full set: Resistance and Strength.", "warrior_boots", "warrior", "boots"),

    VIKING_HELMET("viking_helmet", "Viking Helmet", Material.NETHERITE_HELMET, ChatColor.GOLD,
            "A horned iron helm with leather and bronze details.", "Full set: Strength and Speed.", "viking_helmet", "viking", "helmet"),
    VIKING_CHESTPLATE("viking_chestplate", "Viking Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.GOLD,
            "Layered iron and leather armor with bronze fasteners.", "Full set: Strength and Speed.", "viking_chestplate", "viking", "chestplate"),
    VIKING_LEGGINGS("viking_leggings", "Viking Leggings", Material.NETHERITE_LEGGINGS, ChatColor.GOLD,
            "Leather-backed leg armor with iron knee plates.", "Full set: Strength and Speed.", "viking_leggings", "viking", "leggings"),
    VIKING_BOOTS("viking_boots", "Viking Boots", Material.NETHERITE_BOOTS, ChatColor.GOLD,
            "Fur-lined boots with iron shin guards.", "Full set: Strength and Speed.", "viking_boots", "viking", "boots"),

    GLADIATOR_HELMET("gladiator_helmet", "Gladiator Helmet", Material.NETHERITE_HELMET, ChatColor.RED,
            "A crested arena helm with a strong face guard.", "Full set: Resistance and Speed.", "gladiator_helmet", "gladiator", "helmet"),
    GLADIATOR_CHESTPLATE("gladiator_chestplate", "Gladiator Chestplate", Material.NETHERITE_CHESTPLATE, ChatColor.RED,
            "Bronze arena armor with a red leather sash.", "Full set: Resistance and Speed.", "gladiator_chestplate", "gladiator", "chestplate"),
    GLADIATOR_LEGGINGS("gladiator_leggings", "Gladiator Leggings", Material.NETHERITE_LEGGINGS, ChatColor.RED,
            "Bronze leg guards with reinforced knee plates.", "Full set: Resistance and Speed.", "gladiator_leggings", "gladiator", "leggings"),
    GLADIATOR_BOOTS("gladiator_boots", "Gladiator Boots", Material.NETHERITE_BOOTS, ChatColor.RED,
            "Arena greaves with bronze toe guards.", "Full set: Resistance and Speed.", "gladiator_boots", "gladiator", "boots"),

    // One signature weapon is included in every armor bundle.
    SHADOW_WEAPON("shadow_weapon", "Shadow Daggers", Material.NETHERITE_SWORD, ChatColor.DARK_PURPLE,
            "A pair of quick dark-steel daggers.", "The signature weapon included in the Shadow bundle.", "shadow_weapon", null, null),
    ROYAL_WEAPON("royal_weapon", "Royal War Axe", Material.NETHERITE_AXE, ChatColor.GOLD,
            "A broad royal axe with a brass-edged head.", "The signature weapon included in the Royal bundle.", "royal_weapon", null, null),
    DRAGON_WEAPON("dragon_weapon", "Dragon Greatsword", Material.NETHERITE_SWORD, ChatColor.RED,
            "A heavy dragon-forged greatsword.", "The signature weapon included in the Dragon bundle.", "dragon_weapon", null, null),
    DEMON_WEAPON("demon_weapon", "Demon War Mace", Material.NETHERITE_AXE, ChatColor.DARK_RED,
            "A brutal dark-iron mace with a crimson head.", "The signature weapon included in the Demon bundle.", "demon_weapon", null, null),
    ICE_WEAPON("ice_weapon", "Frost Spear", Material.TRIDENT, ChatColor.AQUA,
            "A long spear tipped with cold steel.", "The signature weapon included in the Ice bundle.", "ice_weapon", null, null),
    ANGEL_WEAPON("angel_weapon", "Angel Lance", Material.TRIDENT, ChatColor.WHITE,
            "A bright lance with an ivory and gold head.", "The signature weapon included in the Angel bundle.", "angel_weapon", null, null),
    WARRIOR_WEAPON("warrior_weapon", "Warrior Battleaxe", Material.NETHERITE_AXE, ChatColor.GRAY,
            "A balanced steel battleaxe with a bronze edge.", "The signature weapon included in the Warrior bundle.", "warrior_weapon", null, null),
    VIKING_WEAPON("viking_weapon", "Viking Warhammer", Material.NETHERITE_AXE, ChatColor.GOLD,
            "A heavy iron warhammer with a wrapped haft.", "The signature weapon included in the Viking bundle.", "viking_weapon", null, null),
    GLADIATOR_WEAPON("gladiator_weapon", "Gladiator Gladius", Material.NETHERITE_SWORD, ChatColor.RED,
            "A short arena sword with a bronze guard.", "The signature weapon included in the Gladiator bundle.", "gladiator_weapon", null, null),

    // A separate mining tool is included with every armor bundle.
    SHADOW_TOOL("shadow_tool", "Shadow Ore Pick", Material.NETHERITE_PICKAXE, ChatColor.DARK_PURPLE,
            "A dark-steel mining pick made for the Shadow bundle.", "A matching bundle tool with high mining speed.", "shadow_tool", null, null),
    ROYAL_TOOL("royal_tool", "Royal Prospector Pick", Material.NETHERITE_PICKAXE, ChatColor.GOLD,
            "A decorated pickaxe with brass fittings for the Royal bundle.", "A matching bundle tool with high mining speed.", "royal_tool", null, null),
    DRAGON_TOOL("dragon_tool", "Dragonbone Pick", Material.NETHERITE_PICKAXE, ChatColor.RED,
            "A dark pick with a dragon-red steel head.", "A matching bundle tool with high mining speed.", "dragon_tool", null, null),
    DEMON_TOOL("demon_tool", "Demon Breaker Pick", Material.NETHERITE_PICKAXE, ChatColor.DARK_RED,
            "A heavy crimson-edged pick for the Demon bundle.", "A matching bundle tool with high mining speed.", "demon_tool", null, null),
    ICE_TOOL("ice_tool", "Frostbreaker Pick", Material.NETHERITE_PICKAXE, ChatColor.AQUA,
            "A pale steel mining pick for the Ice bundle.", "A matching bundle tool with high mining speed.", "ice_tool", null, null),
    ANGEL_TOOL("angel_tool", "Seraph Pick", Material.NETHERITE_PICKAXE, ChatColor.WHITE,
            "An ivory-and-gold pick for the Angel bundle.", "A matching bundle tool with high mining speed.", "angel_tool", null, null),
    WARRIOR_TOOL("warrior_tool", "Warrior Miner Pick", Material.NETHERITE_PICKAXE, ChatColor.GRAY,
            "A practical steel mining pick with bronze rivets.", "A matching bundle tool with high mining speed.", "warrior_tool", null, null),
    VIKING_TOOL("viking_tool", "Viking Stonebreaker", Material.NETHERITE_PICKAXE, ChatColor.GOLD,
            "A rugged iron pick with a leather-wrapped haft.", "A matching bundle tool with high mining speed.", "viking_tool", null, null),
    GLADIATOR_TOOL("gladiator_tool", "Arena Digger Pick", Material.NETHERITE_PICKAXE, ChatColor.RED,
            "A bronze-edged pick crafted for the Gladiator bundle.", "A matching bundle tool with high mining speed.", "gladiator_tool", null, null),

    // Additional weapons for the wider armory.
    WAR_HAMMER("war_hammer", "War Hammer", Material.NETHERITE_AXE, ChatColor.GRAY,
            "A two-handed style hammer for heavy melee combat.", "A heavy battlefield weapon.", "war_hammer", null, null),
    HUNTER_CROSSBOW("hunter_crossbow", "Hunter Crossbow", Material.CROSSBOW, ChatColor.GREEN,
            "A compact crossbow with a reinforced stock.", "A ranged weapon for careful shots.", "hunter_crossbow", null, null),
    BATTLE_SPEAR("battle_spear", "Battle Spear", Material.TRIDENT, ChatColor.GOLD,
            "A long-shafted spear with a forged iron head.", "A reach-focused battlefield weapon.", "battle_spear", null, null);

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

    public static ItemType bundleWeapon(String setId) {
        return fromId(setId == null ? null : setId.toLowerCase() + "_weapon");
    }

    public static ItemType bundleTool(String setId) {
        return fromId(setId == null ? null : setId.toLowerCase() + "_tool");
    }

    public static ItemType fromId(String id) {
        if (id == null || id.isBlank()) return null;
        return Arrays.stream(values())
                .filter(type -> type.id.equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }
}
