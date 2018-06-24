package com.fusenetworks.fuse.banning;

import com.fusenetworks.fuse.Fuse;
import com.fusenetworks.fuse.config.ConfigManager;
import com.fusenetworks.fuse.ranks.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class BanListener implements Listener {
    public BanListener(Fuse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPrePlayerJoin(AsyncPlayerPreLoginEvent event) {
        if (BanManager.isBanned(event.getUniqueId().toString()) && RankManager.isAdminBanListener(event.getUniqueId())) {
            event.allow();
        } else if (BanManager.isBanned(event.getUniqueId().toString()) && !RankManager.isAdminBanListener(event.getUniqueId())) {
            String kickMessage = ChatColor.RED + "You have been banned by: "
                    + ConfigManager.getBans().getConfig().getString(event.getUniqueId()
                    + ".by")
                    + " for: "
                    + ConfigManager.getBans().getConfig().getString(event.getUniqueId()
                    + ".reason");
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, kickMessage);
        }
    }
}
