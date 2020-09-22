package com.ultracore.difficulty;

import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Difficulty implements DifficultyMode {

    abstract double getMultiplier();

    private HashMap<ChanceTypes, Double> applyMultiplier(HashMap<ChanceTypes, Double> chance) {
        chance.replaceAll((t, v) -> chance.get(t) * getMultiplier());
        return chance;
    }

    @Override
    public HashMap<ChanceTypes, Double> getArmorChance() {
        HashMap<ChanceTypes, Double> chance = new LinkedHashMap<>();

        chance.put(ChanceTypes.ARMOR_DIAMOND, 0.05);
        chance.put(ChanceTypes.ARMOR_IRON, 0.15);
        chance.put(ChanceTypes.ARMOR_LEATHER, 0.4);

        return applyMultiplier(chance);
    }

    @Override
    public HashMap<ChanceTypes, Double> getSwordChance() {
        HashMap<ChanceTypes, Double> chance = new LinkedHashMap<>();

        chance.put(ChanceTypes.SWORD_DIAMOND, 0.05);
        chance.put(ChanceTypes.SWORD_IRON, 0.15);
        chance.put(ChanceTypes.SWORD_STONE, 0.2);
        chance.put(ChanceTypes.SWORD_WOOD, 0.3);

        return applyMultiplier(chance);
    }

    @Override
    public HashMap<ChanceTypes, Double> getBowKnockbackEnchantChance() {
        HashMap<ChanceTypes, Double> chance = new LinkedHashMap<>();

        chance.put(ChanceTypes.BOW_ENCHANT_KNOCKBACK_2, 0.2);
        chance.put(ChanceTypes.BOW_ENCHANT_KNOCKBACK_1, 0.6);

        return applyMultiplier(chance);
    }

    @Override
    public HashMap<ChanceTypes, Double> getBowPowerEnchantChance() {
        HashMap<ChanceTypes, Double> chance = new LinkedHashMap<>();

        chance.put(ChanceTypes.BOW_ENCHANT_POWER_4, 0.05);
        chance.put(ChanceTypes.BOW_ENCHANT_POWER_3, 0.15);
        chance.put(ChanceTypes.BOW_ENCHANT_POWER_2, 0.25);
        chance.put(ChanceTypes.BOW_ENCHANT_POWER_1, 0.35);

        return applyMultiplier(chance);
    }

    @Override
    public HashMap<ChanceTypes, Double> getEffectChance() {
        HashMap<ChanceTypes, Double> chance = new LinkedHashMap<>();

        chance.put(ChanceTypes.EFFECT_STRENGTH_2, 0.05);
        chance.put(ChanceTypes.EFFECT_STRENGTH_1, 0.2);

        return applyMultiplier(chance);
    }
}
