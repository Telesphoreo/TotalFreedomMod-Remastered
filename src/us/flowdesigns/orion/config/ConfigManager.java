package com.fusenetworks.fuse.config;

import com.fusenetworks.fuse.Fuse;

import static com.fusenetworks.fuse.Fuse.plugin;

public class ConfigManager {

    private static Config admins;
    private static Config config;
    private static Config bans;

    public ConfigManager() {
        admins = new Config(Fuse.plugin, "admins.yml");
        admins.saveConfig();
        config = new Config(Fuse.plugin, "config.yml");
        config.saveConfig();
        bans = new Config(Fuse.plugin, "bans.yml");
        bans.saveConfig();
    }

    public static Config getAdmin() {
        return admins;
    }

    public static Config getConfig() {
        return config;
    }

    public static Config getBans() {
        return bans;
    }
}