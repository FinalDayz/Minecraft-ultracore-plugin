package com.ultracore;

import com.ultracore.difficulty.EasyDifficulty;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class UltraCore extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(
                new UltraMobSpawner(new EasyDifficulty()),
                this);
    }

    @Override
    public void onDisable() {

    }


}