package com.ultracore;

import com.ultracore.difficulty.Difficulty;
import com.ultracore.difficulty.DifficultyMode;
import com.ultracore.difficulty.EasyDifficulty;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class UltraCore extends JavaPlugin implements Listener {

    private DifficultyMode difficultyMode;

    @Override
    public void onEnable() {
        this.difficultyMode = new EasyDifficulty();
        UltraMobSpawner mobSpawner = new UltraMobSpawner(this);
        MobFightEnhancements mobFightEnchancer = new MobFightEnhancements(this);

        getServer().getPluginManager().registerEvents(mobSpawner, this);
        getServer().getPluginManager().registerEvents(mobFightEnchancer, this);
        getCommand("ultracore").setExecutor(new UltraCoreCommands(this));
    }

    @Override
    public void onDisable() {

    }

    public void setDifficultyMode(DifficultyMode difficultyMode) {
        this.difficultyMode = difficultyMode;
    }

    public DifficultyMode getDifficulty() {
        return this.difficultyMode;
    }
}
