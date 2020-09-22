package com.ultracore;
import com.ultracore.difficulty.ChanceTypes;
import com.ultracore.difficulty.DifficultyMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class UltraMobSpawner implements Listener {

    private final JavaPlugin plugin;
    private DifficultyMode difficultyMode;

    public UltraMobSpawner(JavaPlugin plugin, DifficultyMode difficultyMode) {
        this.plugin = plugin;
        this.difficultyMode = difficultyMode;
    }

    public void setDifficultyMode(DifficultyMode difficultyMode) {
        this.difficultyMode = difficultyMode;
    }

    @EventHandler
    public void monsterSpawn(CreatureSpawnEvent event) {
        if(event.getEntity() instanceof Zombie) {
            Monster entity  = (Monster) event.getEntity();
            enhanceSword((Monster) event.getEntity());
            enhanceArmor((Monster) event.getEntity());
        }

        if(event.getEntity() instanceof Skeleton) {
            enhanceArmor((Monster) event.getEntity());
            enhanceBow((Skeleton) event.getEntity());
        }

        if(event.getEntity() instanceof Monster) {
            enhanceEffect((Monster) event.getEntity());
        }

    }

    private ChanceTypes randomDifficultyValue(HashMap<ChanceTypes, Double> chanceMap) {
        double random = Math.random();
        double totRandom = 0;
        for(ChanceTypes chance : chanceMap.keySet()) {
            totRandom += chanceMap.get(chance);
            if(totRandom >= random) {
                return chance;
            }
        }

        return null;
    }

    private void enhanceBow(Skeleton skeleton) {
        ItemStack bow = new ItemStack(Material.BOW, 1);

        ChanceTypes knockBack = randomDifficultyValue(difficultyMode.getBowKnockbackEnchantChance());
        if(knockBack == ChanceTypes.BOW_ENCHANT_KNOCKBACK_1) {
            bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
        } else if(knockBack == ChanceTypes.BOW_ENCHANT_KNOCKBACK_2) {
            bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        }

        ChanceTypes power = randomDifficultyValue(difficultyMode.getBowPowerEnchantChance());
        if(power == ChanceTypes.BOW_ENCHANT_POWER_1) {
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        } else if(power == ChanceTypes.BOW_ENCHANT_POWER_2) {
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        } else if(power == ChanceTypes.BOW_ENCHANT_POWER_3) {
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        } else if(power == ChanceTypes.BOW_ENCHANT_POWER_4) {
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
        }

        skeleton.getEquipment().setItemInMainHand(bow);
    }

    private void enhanceArmor(Monster monster) {
        monster.getEquipment().setHelmet(
                getArmor(randomDifficultyValue(difficultyMode.getArmorChance()), ArmorType.HELMET)
        );
        monster.getEquipment().setChestplate(
            getArmor(randomDifficultyValue(difficultyMode.getArmorChance()), ArmorType.CHESTPLATE)
        );
        monster.getEquipment().setLeggings(
            getArmor(randomDifficultyValue(difficultyMode.getArmorChance()), ArmorType.LEGGING)
        );
        monster.getEquipment().setBoots(
            getArmor(randomDifficultyValue(difficultyMode.getArmorChance()), ArmorType.BOOTS)
        );
    }

    private void enhanceEffect(Monster monster) {
        ChanceTypes type = randomDifficultyValue(difficultyMode.getEffectChance());
        if(type == ChanceTypes.EFFECT_STRENGTH_1) {
            monster.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
        } else if(type == ChanceTypes.EFFECT_STRENGTH_2) {
            monster.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2));
        }
    }

    ItemStack getArmor(ChanceTypes chanceTypes, ArmorType armorType) {
        if(chanceTypes == null)
            return null;
        Material[] armorSet = null;
        switch(chanceTypes) {
            case ARMOR_LEATHER:
                armorSet =  (new Material[]{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS});
                break;
            case ARMOR_IRON:
                armorSet =  (new Material[]{Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS});
                break;
            case ARMOR_DIAMOND:
                armorSet =  (new Material[]{Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS});
                break;
            default:
                throw new IllegalArgumentException();
        }
        return new ItemStack(armorSet[armorType.ordinal()]);
    }

    private void enhanceSword(Monster monster) {
        ChanceTypes type = randomDifficultyValue(difficultyMode.getSwordChance());
        if(type == ChanceTypes.SWORD_WOOD) {
            monster.getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
        } else if(type == ChanceTypes.SWORD_STONE) {
            monster.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
        } else if(type == ChanceTypes.SWORD_IRON) {
            monster.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        } else if(type == ChanceTypes.SWORD_DIAMOND) {
            monster.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
        }
    }
}
