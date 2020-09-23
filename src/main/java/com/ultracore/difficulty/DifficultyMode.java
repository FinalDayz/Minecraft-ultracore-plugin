package com.ultracore.difficulty;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public interface DifficultyMode {
    HashMap<ChanceTypes, Double> getArmorChance();
    HashMap<ChanceTypes, Double> getSwordChance();
    HashMap<ChanceTypes, Double> getBowKnockbackEnchantChance();
    HashMap<ChanceTypes, Double> getBowPowerEnchantChance();
    HashMap<ChanceTypes, Double> getEffectChance();

    HashMap<EntityType, Double> getMobSpawnChance();
}

