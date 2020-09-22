package com.ultracore.difficulty;

import org.bukkit.Material;

import java.util.HashMap;

public class EasyDifficulty implements DifficultyMode {
    @Override
    public HashMap<ChanceTypes, Double> getArmorChance() {
        HashMap<ChanceTypes, Double> chance = new HashMap<>();

        chance.put(ChanceTypes.ARMOR_LEATHER, 0.4);
        chance.put(ChanceTypes.ARMOR_IRON, 0.15);
        chance.put(ChanceTypes.ARMOR_DIAMOND, 0.05);

        return chance;
    }

    @Override
    public HashMap<ChanceTypes, Double> getWeaponChance() {
        HashMap<ChanceTypes, Double> chance = new HashMap<>();

        chance.put(ChanceTypes.SWORD_WOOD, 0.4);
        chance.put(ChanceTypes.SWORD_STONE, 0.3);
        chance.put(ChanceTypes.SWORD_IRON, 0.2);
        chance.put(ChanceTypes.SWORD_DIAMOND, 0.1);

        return chance;
    }

    @Override
    public HashMap<ChanceTypes, Double> getEffectChance() {
        HashMap<ChanceTypes, Double> chance = new HashMap<>();

        chance.put(ChanceTypes.EFFECT_STRENGTH_1, 0.2);
        chance.put(ChanceTypes.EFFECT_STRENGTH_2, 0.05);

        return chance;
    }
}
