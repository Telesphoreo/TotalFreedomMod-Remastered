package com.fusenetworks.fuse.banning;

import com.fusenetworks.fuse.config.ConfigManager;
import org.bukkit.entity.Player;

public class BanManager {
    public static boolean isBanned(String player) {
        return ConfigManager.getBans().getConfig().contains(player);
    }
}
