package com.fusenetworks.fuse.commands;

import com.fusenetworks.fuse.Fuse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_op implements CommandExecutor {

    private final Fuse plugin;

    public Command_op(Fuse plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 0)
        {
            return false;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player: " + args[0] + " is invalid");
            return true;
        }

        Bukkit.broadcastMessage(ChatColor.GREEN + sender.getName() + " - Opping " + player.getName());
        player.sendMessage(ChatColor.GOLD + "You have been opped by: " + sender.getName());
        player.setOp(true);
        return true;
    }

}