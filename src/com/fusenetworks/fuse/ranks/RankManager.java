package com.fusenetworks.fuse.ranks;

import com.fusenetworks.fuse.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.fusenetworks.fuse.ranks.RankManager.Rank.*;

public class RankManager {

    public enum Rank {
        NON("Non-Op", ChatColor.YELLOW + ""),
        OP("Op", ChatColor.RED + "[OP]"),
        SA("Super Admin", ChatColor.AQUA + "[SA]"),
        STA("Super Telnet Admin", ChatColor.DARK_GREEN + "[STA]"),
        SRA("Senior Admin", ChatColor.GOLD + "[SrA]"),
        OWNER("Owner", ChatColor.BLUE + "[Owner]"),
        CONSOLE("Console", ChatColor.DARK_AQUA + "[Console]");

        private final String name;
        private final String prefix;

        private Rank(String name, String prefix) {
            this.name = name;
            this.prefix = prefix;
        }

        public String getName() {
            return name;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public static boolean isAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().contains(player.getUniqueId().toString());
    }

    public static boolean isAdminBanListener(UUID player) {
        return ConfigManager.getAdmin().getConfig().contains(player.toString());
    }

    public static boolean isSuperAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".admin").equalsIgnoreCase("super");
    }

    public static boolean isTelnetAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".admin").equalsIgnoreCase("telnet");
    }

    public static boolean isSeniorAdmin(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".admin").equalsIgnoreCase("senior");
    }
    public static boolean isOwner(CommandSender sender) {
        Player player = (Player) sender;
        return ConfigManager.getAdmin().getConfig().getString(player.getUniqueId().toString() + ".admin").equalsIgnoreCase("owner");
    }

    public static Rank getRank(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return Rank.CONSOLE;
        }

        final Player player = (Player) sender;

        if (isSuperAdmin(player)) {
            return SA;
        }

        if (isTelnetAdmin(player)) {
            return STA;
        }

        if (isSeniorAdmin(player)) {
            return SRA;
        }
        if (isOwner(player)) {
            return OWNER;
        }
        if (player.isOp()) {
            return OP;
        } else {
            return NON;
        }

    }
}