package us.flowdesigns.orion.commands;

import us.flowdesigns.orion.Orion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_totalfreedommod implements CommandExecutor {
    private final Orion plugin;
    public Command_totalfreedommod(Orion plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "TotalFreedomMod: Remastered");
        sender.sendMessage(ChatColor.GOLD + "Version: " + plugin.version + " " + plugin.codename + " by " + plugin.author);
        return true;
    }

}