package me.benrobson.mcsessify.commands;

import me.benrobson.mcsessify.MCSessifyMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class verify implements CommandExecutor {
    private MCSessifyMain plugin = MCSessifyMain.getInstance();
    public static List<Player> sessionVerifiedUsers = new ArrayList<>();
    public String sessionToken = plugin.getConfig().get("SESSIONTOKEN").toString();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        // Check if token requester is a player
        if (!(commandSender instanceof Player)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.NOTAUSER").toString()));
        }

        // Check if player did not provide a token
        if (strings.length > 0) {
            String playerToken = strings[0];

            if (sessionVerifiedUsers.contains(player)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.USERALREADYVERIFIED").toString()));
            } else {
                // Check if token matches session token
                if (strings[0].equals(sessionToken)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.CORRECTUSERTOKEN").toString().replace("%EVENTNAME%", plugin.getConfig().get("EVENTNAME").toString())));
//                    Bukkit.broadcast(ChatColor.YELLOW + player.getName() + " has joined the server.");
                    sessionVerifiedUsers.add(player);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.INCORRECTUSERTOKEN").toString()));
                }
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.NOUSERTOKEN").toString()));
        }
        return true;
    }

    public static boolean isUserVerified(Player player) {
        if (player == null) return false;

        if (sessionVerifiedUsers.contains(player)) {
            return true;
        } else {
            return false;
        }
    }
}
