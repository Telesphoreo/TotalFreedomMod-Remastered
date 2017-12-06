package com.fusenetworks.fuse.commands;

import com.fusenetworks.fuse.Fuse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_totalfreedommod implements CommandExecutor {
    private final Fuse plugin;
    public Command_totalfreedommod(Fuse plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "TotalFreedomMod: Remastered");
        sender.sendMessage(ChatColor.GOLD + "Version: " + plugin.version + " " + plugin.codename + " by " + plugin.author);
        return true;
    }

}