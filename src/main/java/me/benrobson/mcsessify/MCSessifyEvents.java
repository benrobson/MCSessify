package me.benrobson.mcsessify;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.benrobson.mcsessify.commands.verify;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class MCSessifyEvents implements Listener {
    MCSessifyMain plugin;
    public MCSessifyEvents(MCSessifyMain plugin) {
        this.plugin = plugin;
    }

    // Suppress Player Movement for non-verified
    @EventHandler
    public void UserMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (verify.isUserVerified(player) == false) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.USERNOTVERIFIED").toString()));
            event.setCancelled(true);
        }
    }

    // Suppress Player Item Pickup for non-verified
    @EventHandler
    public void UserPickup(PlayerAttemptPickupItemEvent event) {
        Player player = event.getPlayer();

        if (verify.isUserVerified(player) == false) {
            event.setCancelled(true);
        }
    }

    // Suppress Player Commands for non-verified
    @EventHandler
    public void UserCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String userCommand = event.getMessage();

        if (userCommand.startsWith("/verify")) {
            event.setCancelled(false);
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.USERNOTVERIFIED").toString()));
        }
    }

    // Suppress Player Dropping Items for non-verified
    @EventHandler
    public void UserItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (verify.isUserVerified(player) == false) {
            event.setCancelled(true);
        }
    }

    // Suppress Player Interaction for non-verified
    @EventHandler
    public void UserInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (verify.isUserVerified(player) == false) {
            event.setCancelled(true);
        }
    }

    // Suppress Player Item Holding for non-verified
    @EventHandler
    public void UserItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if (verify.isUserVerified(player) == false) {
            event.setCancelled(true);
        }
    }

    // Suppress Player Block Place for non-verified
    @EventHandler
    public void UserBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (verify.isUserVerified(player) == false) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.USERNOTVERIFIED").toString()));
            event.setCancelled(true);
        }
    }

    // Suppress Player Opening Inventory for non-verified
//    @EventHandler
//    public void UserInventoryOpen(InventoryOpenEvent event) {
//        Player player = (Player) event.getPlayer();
//
//        if (player.getOpenInventory().getType() == InventoryType.PLAYER || player.getOpenInventory().getType() == InventoryType.CREATIVE) {
//            if (verify.isUserVerified(player) == false) {
//                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.USERNOTVERIFIED").toString()));
//                event.getPlayer().closeInventory();
//                event.setCancelled(true);
//            }
//        }
//    }

    // Suppress Player Chat for non-verified
    @EventHandler
    public void UserChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        if (verify.isUserVerified(player) == false) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.USERNOTVERIFIED").toString()));
            event.setCancelled(true);
        }
    }

    // Suppress Player Getting Damaged for non-verified
    @EventHandler
    public void UserDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (verify.isUserVerified(player) == false) {
                event.setCancelled(true);
            }
        }
    }
}
