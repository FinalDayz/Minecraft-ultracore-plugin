package com.ultracore.difficulty;

public class CustomDifficulty extends Difficulty {

    private final double multiplier;

    public CustomDifficulty(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    double getMultiplier() {
        return multiplier;
    }
}
