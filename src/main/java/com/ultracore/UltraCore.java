package com.ultracore;

import com.ultracore.difficulty.Difficulty;
import com.ultracore.difficulty.EasyDifficulty;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class UltraCore extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {

        UltraMobSpawner mobSpawner = new UltraMobSpawner(this, new EasyDifficulty());

        getServer().getPluginManager().registerEvents(mobSpawner, this);
        getCommand("ultracore").setExecutor(new UltraCoreCommands(mobSpawner));
    }

    @Override
    public void onDisable() {

    }


}