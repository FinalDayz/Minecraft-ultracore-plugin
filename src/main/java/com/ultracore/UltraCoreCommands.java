package com.ultracore;

import com.ultracore.difficulty.EasyDifficulty;
import com.ultracore.difficulty.HardDifficulty;
import com.ultracore.difficulty.MediumDifficulty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UltraCoreCommands implements CommandExecutor {
    private final UltraMobSpawner mobSpawner;

    public UltraCoreCommands(UltraMobSpawner mobSpawner) {
        this.mobSpawner = mobSpawner;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1)
            return false;

        String diffifulty = args[0];

        if(diffifulty.equals("easy")) {
            this.mobSpawner.setDifficultyMode(new EasyDifficulty());
            sender.sendMessage("Successfully changed difficulty to easy");
            return true;
        } else if(diffifulty.equals("medium")) {
            this.mobSpawner.setDifficultyMode(new MediumDifficulty());
            sender.sendMessage("Successfully changed difficulty to medium");
            return true;
        } else if(diffifulty.equals("hard")) {
            this.mobSpawner.setDifficultyMode(new HardDifficulty());
            sender.sendMessage("Successfully changed difficulty to hard");
            return true;
        }
        return false;
    }
}
