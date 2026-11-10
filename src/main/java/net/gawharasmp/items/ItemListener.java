package net.gawharasmp.items;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ItemListener implements Listener {
    private final GawharaItemsPlugin plugin;
    private final ItemFactory itemFactory;
    private final Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();

    public ItemListener(GawharaItemsPlugin plugin, ItemFactory itemFactory) {
        this.plugin = plugin;
        this.itemFactory = itemFactory;
        plugin.getServer().getScheduler().runTaskTimer(plugin, this::applyArmorSetPassives, 20L, 40L);
    }

    @EventHandler
    public void onCustomItemUse(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;

        Player player = event.getPlayer();
        ItemType type = itemFactory.getType(event.getItem());
        if (type == null) return;

        if (type.isAbilitySword() || type == ItemType.LIGHTNING_WAND || type == ItemType.HEALING_WAND) {
            event.setCancelled(true);
        } else {
            return;
        }
        if (!startCooldown(player, type)) return;

        switch (type) {
            case FLAME_BLADE -> flameBurst(player,
                    plugin.getConfig().getDouble("abilities.flame_blade.radius", 5.0),
                    plugin.getConfig().getDouble("abilities.flame_blade.damage", 4.0),
                    plugin.getConfig().getInt("abilities.flame_blade.fire_ticks", 100), ChatColor.GOLD);
            case FROST_BLADE -> frostBurst(player,
                    plugin.getConfig().getDouble("abilities.frost_blade.radius", 5.0),
                    plugin.getConfig().getDouble("abilities.frost_blade.damage", 2.0),
                    plugin.getConfig().getInt("abilities.frost_blade.duration_ticks", 100),
                    plugin.getConfig().getInt("abilities.frost_blade.amplifier", 1));
            case LIGHTNING_BLADE -> lightningBurst(player,
                    plugin.getConfig().getInt("abilities.lightning_blade.range", 28),
                    plugin.getConfig().getDouble("abilities.lightning_blade.radius", 4.0),
                    plugin.getConfig().getDouble("abilities.lightning_blade.damage", 7.0));
            case SHADOW_BLADE -> shadowBurst(player);
            case VENOM_BLADE -> venomBurst(player);
            case VITAL_BLADE -> vitalBurst(player);
            case DRAGON_BLADE -> dragonBurst(player);
            case SPIRIT_BLADE -> spiritBurst(player);
            case LIGHTNING_WAND -> lightningBurst(player,
                    plugin.getConfig().getInt("abilities.lightning_wand.range", 25),
                    plugin.getConfig().getDouble("abilities.lightning_wand.radius", 3.0),
                    plugin.getConfig().getDouble("abilities.lightning_wand.damage", 6.0));
            case HEALING_WAND -> healPlayer(player, plugin.getConfig().getDouble("abilities.healing_wand.heal_amount", 6.0), 0);
            default -> { }
        }
    }

    private void flameBurst(Player player, double radius, double damage, int fireTicks, ChatColor color) {
        int affected = 0;
        for (Monster monster : nearbyMonsters(player, radius)) {
            monster.setFireTicks(Math.max(monster.getFireTicks(), fireTicks));
            monster.damage(damage, player);
            affected++;
        }
        message(player, color, "Flame Burst hit " + affected + " monster(s).");
    }

    private void frostBurst(Player player, double radius, double damage, int duration, int amplifier) {
        int affected = 0;
        for (Monster monster : nearbyMonsters(player, radius)) {
            monster.damage(damage, player);
            monster.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, amplifier));
            affected++;
        }
        message(player, ChatColor.AQUA, "Frost Burst slowed " + affected + " monster(s).");
    }

    private void lightningBurst(Player player, int range, double radius, double damage) {
        var target = player.getTargetBlockExact(range);
        if (target == null) {
            message(player, ChatColor.RED, "No block found within " + range + " blocks.");
            return;
        }
        Location strike = target.getLocation().add(0.5, 1.0, 0.5);
        player.getWorld().strikeLightningEffect(strike);
        int affected = 0;
        for (Entity entity : player.getWorld().getNearbyEntities(strike, radius, radius, radius)) {
            if (entity instanceof Monster monster) { monster.damage(damage, player); affected++; }
        }
        message(player, ChatColor.YELLOW, "Lightning summoned! Hit " + affected + " monster(s).");
    }

    private void shadowBurst(Player player) {
        int affected = 0;
        double radius = plugin.getConfig().getDouble("abilities.shadow_blade.radius", 6.0);
        double damage = plugin.getConfig().getDouble("abilities.shadow_blade.damage", 3.0);
        int duration = plugin.getConfig().getInt("abilities.shadow_blade.duration_ticks", 100);
        for (Monster monster : nearbyMonsters(player, radius)) {
            monster.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 0));
            monster.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration, 1));
            monster.damage(damage, player);
            affected++;
        }
        message(player, ChatColor.DARK_PURPLE, "Umbral Burst blinded and weakened " + affected + " monster(s).");
    }

    private void venomBurst(Player player) {
        int affected = 0;
        double radius = plugin.getConfig().getDouble("abilities.venom_blade.radius", 5.0);
        double damage = plugin.getConfig().getDouble("abilities.venom_blade.damage", 2.0);
        int duration = plugin.getConfig().getInt("abilities.venom_blade.duration_ticks", 140);
        for (Monster monster : nearbyMonsters(player, radius)) {
            monster.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, 1));
            monster.damage(damage, player);
            affected++;
        }
        message(player, ChatColor.DARK_GREEN, "Venom Burst poisoned " + affected + " monster(s).");
    }

    private void vitalBurst(Player player) {
        int affected = 0;
        double radius = plugin.getConfig().getDouble("abilities.vital_blade.radius", 5.0);
        double damage = plugin.getConfig().getDouble("abilities.vital_blade.damage", 4.0);
        for (Monster monster : nearbyMonsters(player, radius)) {
            monster.damage(damage, player);
            affected++;
        }
        healPlayer(player, plugin.getConfig().getDouble("abilities.vital_blade.heal_amount", 8.0), 100);
        message(player, ChatColor.LIGHT_PURPLE, "Lifebinder struck " + affected + " monster(s) and restored your vitality.");
    }

    private void dragonBurst(Player player) {
        int affected = 0;
        double radius = plugin.getConfig().getDouble("abilities.dragon_blade.radius", 6.0);
        double damage = plugin.getConfig().getDouble("abilities.dragon_blade.damage", 6.0);
        int fireTicks = plugin.getConfig().getInt("abilities.dragon_blade.fire_ticks", 160);
        for (Monster monster : nearbyMonsters(player, radius)) {
            monster.setFireTicks(Math.max(monster.getFireTicks(), fireTicks));
            monster.damage(damage, player);
            monster.setVelocity(monster.getLocation().toVector().subtract(player.getLocation().toVector())
                    .normalize().multiply(0.65).setY(0.25));
            affected++;
        }
        message(player, ChatColor.RED, "Dragonfire shockwave struck " + affected + " monster(s).");
    }

    private void spiritBurst(Player player) {
        int affected = 0;
        double radius = plugin.getConfig().getDouble("abilities.spirit_blade.radius", 6.0);
        double damage = plugin.getConfig().getDouble("abilities.spirit_blade.damage", 3.0);
        int duration = plugin.getConfig().getInt("abilities.spirit_blade.duration_ticks", 120);
        for (Monster monster : nearbyMonsters(player, radius)) {
            monster.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, duration, 0));
            monster.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration, 0));
            monster.damage(damage, player);
            affected++;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 160, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
        message(player, ChatColor.WHITE, "Spirit Wave marked " + affected + " monster(s) and shielded your soul.");
    }

    private void healPlayer(Player player, double amount, int regenerationTicks) {
        var maxHealth = player.getAttribute(Attribute.MAX_HEALTH);
        double max = maxHealth == null ? 20.0 : maxHealth.getValue();
        double before = player.getHealth();
        player.setHealth(Math.min(max, before + Math.max(0.0, amount)));
        if (regenerationTicks > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, regenerationTicks, 1));
        message(player, ChatColor.LIGHT_PURPLE, "Restored " + String.format("%.1f", player.getHealth() - before) + " health.");
    }

    private java.util.List<Monster> nearbyMonsters(Player player, double radius) {
        return player.getNearbyEntities(radius, radius, radius).stream()
                .filter(Monster.class::isInstance).map(Monster.class::cast).toList();
    }

    @EventHandler
    public void onCustomToolBreak(BlockBreakEvent event) {
        ItemType type = itemFactory.getType(event.getPlayer().getInventory().getItemInMainHand());
        if (type == ItemType.MINER_PICKAXE && event.getBlock().getType().name().endsWith("_ORE")) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 60, 0));
        } else if (type == ItemType.LUMBER_AXE) {
            String material = event.getBlock().getType().name();
            if (material.endsWith("_LOG") || material.endsWith("_WOOD")) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 60, 0));
            }
        }
    }

    @EventHandler
    public void onArmorDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        int guardianPieces = 0;
        for (ItemStack piece : player.getInventory().getArmorContents()) {
            ItemType type = itemFactory.getType(piece);
            if (type == ItemType.GUARDIAN_HELMET || type == ItemType.GUARDIAN_CHESTPLATE
                    || type == ItemType.GUARDIAN_LEGGINGS || type == ItemType.GUARDIAN_BOOTS) guardianPieces++;
        }
        double reduction = 0.0;
        if (guardianPieces > 0) {
            double perPiece = plugin.getConfig().getDouble("guardian_armor.damage_reduction_per_piece", 0.025);
            double maximum = plugin.getConfig().getDouble("guardian_armor.maximum_reduction", 0.10);
            reduction += Math.min(maximum, guardianPieces * perPiece);
        }

        String set = getCompleteArmorSet(player);
        if (set != null) {
            reduction += plugin.getConfig().getDouble("armor_sets." + set + ".damage_reduction", 0.08);
            if (set.equals("dragon") && (event.getCause() == EntityDamageEvent.DamageCause.FIRE
                    || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK
                    || event.getCause() == EntityDamageEvent.DamageCause.LAVA)) {
                event.setDamage(0.0);
                return;
            }
        }
        if (reduction > 0) event.setDamage(event.getDamage() * (1.0 - Math.min(0.35, reduction)));
    }

    @EventHandler
    public void onIceArmorCounter(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!"ice".equals(getCompleteArmorSet(player))) return;
        if (event.getDamager() instanceof LivingEntity attacker) {
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 0));
        }
    }

    private void applyArmorSetPassives() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            String set = getCompleteArmorSet(player);
            if (set == null) continue;
            switch (set) {
                case "shadow" -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1));
                    if (player.isSneaking()) player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60, 0));
                }
                case "royal" -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 60, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));
                }
                case "dragon" -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 60, 0));
                }
                case "demon" -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 60, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 0));
                }
                case "ice" -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 60, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 0));
                }
                case "angel" -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 60, 1));
                }
                default -> { }
            }
        }
    }

    private String getCompleteArmorSet(Player player) {
        Map<String, Integer> counts = new HashMap<>();
        for (ItemStack piece : player.getInventory().getArmorContents()) {
            ItemType type = itemFactory.getType(piece);
            if (type != null && type.isArmorPiece()) counts.merge(type.getArmorSet(), 1, Integer::sum);
        }
        return counts.entrySet().stream().filter(entry -> entry.getValue() >= 4)
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }

    private boolean startCooldown(Player player, ItemType type) {
        int seconds = Math.max(1, plugin.getConfig().getInt("cooldowns." + type.getId(), 10));
        long now = System.currentTimeMillis();
        Map<String, Long> playerCooldowns = cooldowns.computeIfAbsent(player.getUniqueId(), ignored -> new HashMap<>());
        long lastUsed = playerCooldowns.getOrDefault(type.getId(), 0L);
        long remaining = seconds * 1000L - (now - lastUsed);
        if (remaining > 0) {
            player.sendMessage(prefix() + ChatColor.RED + "Ability ready in " + String.format("%.1f", remaining / 1000.0) + "s.");
            return false;
        }
        playerCooldowns.put(type.getId(), now);
        return true;
    }

    private void message(Player player, ChatColor color, String message) { player.sendMessage(prefix() + color + message); }
    private String prefix() {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("settings.prefix", "&bGawharaItems &8» "));
    }
}
