package me.benrobson.mcsessify;

import me.benrobson.mcsessify.commands.token;
import me.benrobson.mcsessify.commands.verify;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCSessifyMain extends JavaPlugin {
    public static MCSessifyMain plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Init Message
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nMCSessify has been enabled.\nRunning Version " + plugin.getDescription().getVersion() + "\nGitHub Repository: https://github.com/benrobson/MCSessify\nCreated by Ben Robson\n\n");

        this.getCommand("verify").setExecutor(new verify());
        this.getCommand("token").setExecutor(new token());

        PluginManager pluginmanager = this.getServer().getPluginManager();
        pluginmanager.registerEvents(new MCSessifyEvents(this), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nMCSessify has been disabled.\n\n");
        plugin.saveConfig();
    }

    public static MCSessifyMain getInstance() {
        return plugin;
    }

    private static void setInstance(MCSessifyMain instance) {
        MCSessifyMain.plugin = instance;
    }
}
