package com.fusenetworks.fuse;

import com.fusenetworks.fuse.commands.*;
import com.fusenetworks.fuse.config.ConfigManager;
import com.fusenetworks.fuse.ranks.RankListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Fuse extends JavaPlugin {
    public static Fuse plugin;
    PluginManager pm = getServer().getPluginManager();

    @Override
    public void onLoad() {
        plugin = this;

    }

    @Override
    public void onEnable() {
        register();
    }

    @Override
    public void onDisable() {
    }

    public void register() {
        new ConfigManager();
        ConfigManager.getAdmin().saveDefaultConfig();
        ConfigManager.getConfig().saveDefaultConfig();
        pm.registerEvents(new RankListener(plugin), this);
        this.getCommand("admin").setExecutor(new Command_admin(this));
        this.getCommand("gtfo").setExecutor(new Command_gtfo(this));
        this.getCommand("list").setExecutor(new Command_list(this));
        this.getCommand("op").setExecutor(new Command_op(this));
        this.getCommand("totalfreedommod").setExecutor(new Command_totalfreedommod(this));
    }

    public String version = "0.1";
    public String codename = "Andromeda";
    public String author = "Telesphoreo";
}