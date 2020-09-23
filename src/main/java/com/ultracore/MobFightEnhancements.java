package com.ultracore;

import com.ultracore.difficulty.DifficultyMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class MobFightEnhancements implements Listener {

    private final UltraCore plugin;

    public MobFightEnhancements(UltraCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType() != EntityType.PLAYER &&
                event.getDamager().getType() != EntityType.ARROW)
            return;
        Entity entity = event.getEntity();

        if(entity.hasMetadata("isSubEntity"))
            return;

        HashMap<EntityType, Double> chances = plugin.getDifficulty().getMobSpawnChance();
        for(EntityType entityType : chances.keySet()) {
            if(entity.getType() == entityType && isChance(chances.get(entityType))) {

                Location location = event.getEntity().getLocation();
                location.subtract(event.getEntity().getFacing().getDirection().normalize().multiply(5));
                location = getSpawnableLocation(location, 0, 2);

                Monster newEntity = (Monster) event.getEntity().getWorld().spawnEntity(
                        location,
                        entityType
                );
                newEntity.setMetadata("isSubEntity", new FixedMetadataValue(plugin, true));
                newEntity.setMaxHealth(newEntity.getMaxHealth()*0.3);
                newEntity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
            }
        }
    }

    Location getSpawnableLocation(Location location, double minRadius, double maxRadius) {
        Location newLoc = location.clone();
        randomNearbyLocation(newLoc, 2, 4);
        if(toGround(newLoc) == null) {
            return this.getSpawnableLocation(location, minRadius+0.1, maxRadius+0.1);
        }
        return newLoc;
    }

    Location toGround(Location location) {
        for(double y = location.getY()-3; y < location.getY()+3; y++) {
            location.setY(y);
            if(location.getBlock().getType() == Material.AIR) {
                return location;
            }
        }
        return null;
    }


    Location randomNearbyLocation(Location location, double minRadius, int maxRadius) {
        Vector vector = new Vector(Math.random()-0.5, Math.random()-0.5, 0);
        vector.normalize();
        vector.multiply(Math.random() * (maxRadius - minRadius) + minRadius);
        location.add(vector);

        return location.add(vector);
    }

    private boolean isChance(double chance) {
        return (Math.random() < chance);
    }
}
