package com.ultracore.difficulty;

import org.bukkit.Material;
import java.util.HashMap;

public interface DifficultyMode {
    HashMap<ChanceTypes, Double> getArmorChance();
    HashMap<ChanceTypes, Double> getWeaponChance();
    HashMap<ChanceTypes, Double> getEffectChance();
}

