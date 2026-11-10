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
    }

    @EventHandler
    public void onCustomItemUse(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        ItemType type = itemFactory.getType(event.getItem());
        if (type == null) {
            return;
        }

        switch (type) {
            case FLAME_BLADE -> {
                event.setCancelled(true);
                if (!startCooldown(player, type)) return;
                double radius = plugin.getConfig().getDouble("abilities.flame_blade.radius", 5.0);
                double damage = plugin.getConfig().getDouble("abilities.flame_blade.damage", 4.0);
                int fireTicks = plugin.getConfig().getInt("abilities.flame_blade.fire_ticks", 100);
                int affected = 0;
                for (Entity entity : player.getNearbyEntities(radius, radius, radius)) {
                    if (entity instanceof Monster monster) {
                        monster.setFireTicks(Math.max(monster.getFireTicks(), fireTicks));
                        monster.damage(damage, player);
                        affected++;
                    }
                }
                player.sendMessage(prefix() + ChatColor.GOLD
                        + "Flame Burst hit " + affected + " monster(s).");
            }
            case FROST_BLADE -> {
                event.setCancelled(true);
                if (!startCooldown(player, type)) return;
                double radius = plugin.getConfig().getDouble("abilities.frost_blade.radius", 5.0);
                double damage = plugin.getConfig().getDouble("abilities.frost_blade.damage", 2.0);
                int duration = plugin.getConfig().getInt("abilities.frost_blade.duration_ticks", 100);
                int amplifier = plugin.getConfig().getInt("abilities.frost_blade.amplifier", 1);
                int affected = 0;
                for (Entity entity : player.getNearbyEntities(radius, radius, radius)) {
                    if (entity instanceof Monster monster) {
                        monster.damage(damage, player);
                        monster.addPotionEffect(new PotionEffect(
                                PotionEffectType.SLOWNESS, duration, amplifier));
                        affected++;
                    }
                }
                player.sendMessage(prefix() + ChatColor.AQUA
                        + "Frost Burst slowed " + affected + " monster(s).");
            }
            case LIGHTNING_WAND -> {
                event.setCancelled(true);
                if (!startCooldown(player, type)) return;
                int range = plugin.getConfig().getInt("abilities.lightning_wand.range", 25);
                var target = player.getTargetBlockExact(range);
                if (target == null) {
                    player.sendMessage(prefix() + ChatColor.RED
                            + "No block found within " + range + " blocks.");
                    return;
                }
                Location strikeLocation = target.getLocation().add(0.5, 1.0, 0.5);
                player.getWorld().strikeLightningEffect(strikeLocation);
                double radius = plugin.getConfig().getDouble("abilities.lightning_wand.radius", 3.0);
                double damage = plugin.getConfig().getDouble("abilities.lightning_wand.damage", 6.0);
                int affected = 0;
                for (Entity entity : player.getWorld().getNearbyEntities(strikeLocation, radius, radius, radius)) {
                    if (entity instanceof Monster monster) {
                        monster.damage(damage, player);
                        affected++;
                    }
                }
                player.sendMessage(prefix() + ChatColor.YELLOW
                        + "Lightning summoned! Hit " + affected + " monster(s).");
            }
            case HEALING_WAND -> {
                event.setCancelled(true);
                if (!startCooldown(player, type)) return;
                var maxHealth = player.getAttribute(Attribute.MAX_HEALTH);
                double max = maxHealth == null ? 20.0 : maxHealth.getValue();
                double before = player.getHealth();
                double heal = plugin.getConfig().getDouble("abilities.healing_wand.heal_amount", 6.0);
                player.setHealth(Math.min(max, before + Math.max(0.0, heal)));
                double restored = player.getHealth() - before;
                player.sendMessage(prefix() + ChatColor.LIGHT_PURPLE
                        + "Healing Wand restored " + String.format("%.1f", restored) + " health.");
            }
            default -> {
                // Mining tools and Guardian armor are handled by their passive events.
            }
        }
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
    public void onGuardianArmorDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        int pieces = 0;
        for (ItemStack armorPiece : player.getInventory().getArmorContents()) {
            ItemType type = itemFactory.getType(armorPiece);
            if (type == ItemType.GUARDIAN_HELMET
                    || type == ItemType.GUARDIAN_CHESTPLATE
                    || type == ItemType.GUARDIAN_LEGGINGS
                    || type == ItemType.GUARDIAN_BOOTS) {
                pieces++;
            }
        }

        if (pieces == 0) {
            return;
        }
        double perPiece = plugin.getConfig().getDouble(
                "guardian_armor.damage_reduction_per_piece", 0.025);
        double maximum = plugin.getConfig().getDouble(
                "guardian_armor.maximum_reduction", 0.10);
        double reduction = Math.min(maximum, pieces * perPiece);
        event.setDamage(event.getDamage() * (1.0 - Math.max(0.0, reduction)));
    }

    private boolean startCooldown(Player player, ItemType type) {
        int seconds = Math.max(1,
                plugin.getConfig().getInt("cooldowns." + type.getId(), 10));
        long now = System.currentTimeMillis();
        Map<String, Long> playerCooldowns = cooldowns.computeIfAbsent(
                player.getUniqueId(), ignored -> new HashMap<>());
        long lastUsed = playerCooldowns.getOrDefault(type.getId(), 0L);
        long remaining = seconds * 1000L - (now - lastUsed);

        if (remaining > 0) {
            player.sendMessage(prefix() + ChatColor.RED + "Ability ready in "
                    + String.format("%.1f", remaining / 1000.0) + "s.");
            return false;
        }

        playerCooldowns.put(type.getId(), now);
        return true;
    }

    private String prefix() {
        return ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("settings.prefix", "&bGawharaItems &8» "));
    }
}
