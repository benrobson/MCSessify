package me.benrobson.mcsessify;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.benrobson.mcsessify.commands.verify;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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

        if (userCommand.startsWith("/verify") || userCommand.startsWith("/token") || userCommand.startsWith("/reload") || verify.isUserVerified(player)) {
            event.setCancelled(false);
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.PREFIX").toString()) + " " + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().get("LANG.USERNOTVERIFIED").toString()));
            event.setCancelled(true);
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
    @EventHandler(priority = EventPriority.HIGHEST)
    public void UserInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check for a left and right click
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.PHYSICAL) {
            if (verify.isUserVerified(player) == false) {
                event.setCancelled(true);
            }
        }

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

    // Suppress Player join message
    @EventHandler
    public void UserJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
    }

//    // Suppress Player Inventory for non-verified
//    @EventHandler(priority = EventPriority.HIGHEST)
//    public void UserClickEvent(InventoryClickEvent event) {
//        Player player = event.getWhoClicked().getKiller();
//        ItemStack clicked = event.getCurrentItem();
//
//        if (clicked.getType() == Material.LEATHER_HELMET || clicked.getType() == Material.COMPASS || clicked.getType() == Material.CHEST || clicked.getType() == Material.CLOCK) {
//            if (verify.isUserVerified(player) == false) {
//                event.getInventory().close();
//                event.setCancelled(true);
//            }
//        }
//
//        if (verify.isUserVerified(player) == false) {
//            event.getInventory().close();
//            event.setCancelled(true);
//        }
//    }

//    public void UserInventoryOpen(InventoryOpenEvent event) {
//        Player player = (Player) event.getPlayer();
//
//        if (verify.isUserVerified(player) == false) {
//            event.getInventory().close();
//            event.setCancelled(true);
//        }
//    }
}
