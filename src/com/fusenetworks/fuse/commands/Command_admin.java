package com.fusenetworks.fuse.commands;

import com.fusenetworks.fuse.Fuse;
import com.fusenetworks.fuse.config.ConfigManager;
import com.fusenetworks.fuse.ranks.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_admin implements CommandExecutor {

    private final Fuse plugin;

    public Command_admin(Fuse plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }
        if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage(ChatColor.RED + "Admin list is currently under development");
            return true;
        }
        Player player = Bukkit.getPlayer(args[1]);
        if (args.length == 2) {
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "Player: " + args[1] + " is invalid");
                return true;
            }

            if (args[0].equalsIgnoreCase("add")) {
                Bukkit.broadcastMessage(ChatColor.GREEN + sender.getName() + " - Adding " + player.getName() + " to the superadmin list");
                ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".name", player.getName());
                ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".ip", player.getAddress().getHostString());
                ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".admin", "super");
                ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".tag", "");
                ConfigManager.getAdmin().saveConfig();
                return true;
            }

            if (args[0].equalsIgnoreCase("delete")) {
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Removing " + player.getName() + " from the superadmin list");
                ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString(), null);
                ConfigManager.getAdmin().saveConfig();
                return true;
            }
            return false;
        } else if (args.length == 3) {
            if (RankManager.isSeniorAdmin(sender)) {
                if (args[0].equalsIgnoreCase("setrank")) {
                    if (args[3] != "super" || args[3] != "telnet" || args[3] != "senior") {
                        sender.sendMessage(ChatColor.RED + "Invalid rank! Please use super, telnet, or senior.");
                        return true;
                    } else {
                        ConfigManager.getAdmin().getConfig().set(player.getUniqueId().toString() + ".admin", args[3]);
                    }
                } else {
                    return false;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You must be a Senior Admin to perform this action");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use /admin");
        }
        return true;
    }
}