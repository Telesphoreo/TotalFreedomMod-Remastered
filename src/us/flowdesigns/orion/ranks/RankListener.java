package com.fusenetworks.fuse.ranks;

import com.fusenetworks.fuse.Fuse;
import com.fusenetworks.fuse.ranks.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RankListener implements Listener
{
    public RankListener(Fuse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (RankManager.isSuperAdmin(player)) {
            Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " is a " + ChatColor.ITALIC + "Super Admin");
        }
        if (RankManager.isTelnetAdmin(player)) {
            Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " is a " + ChatColor.GREEN + ChatColor.ITALIC + "Telnet Admin");
        }
        if (RankManager.isSeniorAdmin(player)) {
            Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " is a " + ChatColor.GOLD + ChatColor.ITALIC + "Senior Admin");
        }
        if (RankManager.isOwner(player)) {
            Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " is the " + ChatColor.BLUE + ChatColor.ITALIC + "Owner");
        }
    }
}