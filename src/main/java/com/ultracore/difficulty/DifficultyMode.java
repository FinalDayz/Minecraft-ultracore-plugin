package com.ultracore.difficulty;

import org.bukkit.Material;
import java.util.HashMap;

public interface DifficultyMode {
    HashMap<ChanceTypes, Double> getArmorChance();
    HashMap<ChanceTypes, Double> getSwordChance();
    HashMap<ChanceTypes, Double> getBowKnockbackEnchantChance();
    HashMap<ChanceTypes, Double> getBowPowerEnchantChance();
    HashMap<ChanceTypes, Double> getEffectChance();
}

