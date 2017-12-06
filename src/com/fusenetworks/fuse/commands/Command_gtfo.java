package com.fusenetworks.fuse.commands;

import com.fusenetworks.fuse.Fuse;
import com.fusenetworks.fuse.config.ConfigManager;
import com.fusenetworks.fuse.ranks.RankManager;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_gtfo implements CommandExecutor {
    private final Fuse plugin;

    public Command_gtfo(Fuse plugin) {
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
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(ChatColor.RED + "Invalid player");
            return true;
        }
        String reason = null;
        if (args.length >= 2)
        {
            reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        } else {
            reason = "Unknown reason";
        }
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".name", player.getName());
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".ip", player.getAddress().getHostString());
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".reason", reason);
        ConfigManager.getBans().getConfig().set(player.getUniqueId().toString() + ".banned", "true");
        ConfigManager.getBans().saveConfig();
        player.kickPlayer(ChatColor.RED + "You have been banned by: " + sender.getName() + " for: " + reason);
        return true;
    }
}