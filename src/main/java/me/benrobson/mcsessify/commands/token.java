package me.benrobson.mcsessify.commands;

import me.benrobson.mcsessify.MCSessifyMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class token implements CommandExecutor {
    private MCSessifyMain plugin = MCSessifyMain.getInstance();
    public String sessionToken = plugin.getConfig().get("SESSIONTOKEN").toString();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        if (strings.length > 0) {
            switch (strings[0]) {
                case "view":
                    // View the current token
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.TOKENCURRENT").toString().replace("%TOKEN%", sessionToken)));
                    break;
                case "set":
                    // Set a new token
                    if (strings.length > 1) {
                        String newToken = strings[1];
                        plugin.getConfig().set("SESSIONTOKEN", newToken);
                        plugin.saveConfig();
                        plugin.reloadConfig();

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.NEWTOKENSET").toString()));
                        return true;
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.NONEWTOKEN").toString()));
                    }
                    break;
                default:
                    // If there is no case
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.TOKENARGSOPTIONS").toString()));
            return true;
        }
        return true;
    }
}
