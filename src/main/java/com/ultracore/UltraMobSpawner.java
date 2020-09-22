package com.ultracore;

import com.sun.istack.internal.Nullable;
import com.ultracore.difficulty.ChanceTypes;
import com.ultracore.difficulty.DifficultyMode;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class UltraMobSpawner implements Listener {

    private DifficultyMode difficultyMode;

    public UltraMobSpawner(DifficultyMode difficultyMode) {
        this.difficultyMode = difficultyMode;
    }

    @EventHandler
    public void monsterSpawn(CreatureSpawnEvent event) {
        if(event.getEntity() instanceof Zombie) {
            Monster entity  = (Monster) event.getEntity();
            enhanceZombie((Zombie) event.getEntity());
        }

        if(event.getEntity() instanceof Monster) {
            enhanceArmor((Monster) event.getEntity());
        }
    }

    @Nullable
    private ChanceTypes randomDifficultyValue(HashMap<ChanceTypes, Double> chanceMap) {
        double random = Math.random();
        double totRandom = 0;
        for(ChanceTypes chance : chanceMap.keySet()) {
            totRandom += chanceMap.get(chance);
            if(totRandom < random) {
                return chance;
            }
        }

        return null;
    }

    private void enhanceArmor(Monster monster) {
        ChanceTypes type = randomDifficultyValue(difficultyMode.getArmorChance());
        monster.getEquipment().setArmorContents(new ItemStack[]{

        });
    }

//    typeToItemStack(ChanceTypes type, )

    private void enhanceZombie(Zombie zombie) {
        zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
    }
}
