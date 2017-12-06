package com.fusenetworks.fuse.commands;

import com.fusenetworks.fuse.Fuse;
import com.fusenetworks.fuse.ranks.RankManager;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Command_list implements CommandExecutor {

    private final Fuse plugin;

    public Command_list(Fuse plugin) {
        this.plugin = plugin;
    }

    private enum Filter {
        ALL,
        ADMIN;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 1) {
            return false;
        }

        final Filter filter;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("-a")) {
                filter = Filter.ADMIN;
            } else {
                return false;
            }
        } else {
            filter = Filter.ALL;
        }

        final List<String> names = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (filter == Filter.ADMIN && !RankManager.isAdmin(player)) {
                continue;
            }

            names.add(RankManager.getRank(player).getPrefix() + player.getName());
        }

        sender.sendMessage(ChatColor.AQUA + "There are " + ChatColor.RED + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ChatColor.AQUA + " players online!");
        sender.sendMessage("Connected players: ");
        sender.sendMessage(StringUtils.join(names, ChatColor.WHITE, ", ").replaceAll(" [ ]", ""));
        return true;
    }
}