package me.benrobson.mcsessify;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MCSessifyMain extends JavaPlugin {
    public static MCSessifyMain plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Init Message
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nMCSessify has been enabled.\nRunning Version " + plugin.getDescription().getVersion() + "\nGitHub Repository: https://github.com/benrobson/MCSessify\nCreated by Ben Robson\n\n");

//        this.getCommand("verify").setExecutor(new verify());
//        this.getCommand("createtoken").setExecutor(new createtoken());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nMCSessify has been disabled.\n\n");
    }
}
