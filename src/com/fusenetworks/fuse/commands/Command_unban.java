package com.fusenetworks.fuse.commands;

import com.fusenetworks.fuse.Fuse;
import com.fusenetworks.fuse.config.ConfigManager;
import com.fusenetworks.fuse.ranks.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_unban implements CommandExecutor {
    private final Fuse plugin;
    public Command_unban(Fuse plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length < 0)
        {
            return false;
        }
        if (!RankManager.isAdmin(sender)) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString(), null);
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".name", null);
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".ip", null);
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".by", null);
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".reason", null);
        ConfigManager.getBans().saveConfig();
        Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Unbanning " + args[0]);
        return true;
    }

}