package moe.satori.wschat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class events implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String json = utils.toJSON(Map.of(
                "method", "join",
                "username", player.getName(),
                "uuid", player.getUniqueId().toString(),
                "ip", player.getAddress().getHostString()
        ));
        if (main.connected) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    main.client.send(json);
                }
            }.runTaskAsynchronously(main.main);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String json = utils.toJSON(Map.of(
                "method", "quit",
                "username", player.getName(),
                "uuid", player.getUniqueId().toString(),
                "ip", player.getAddress().getHostString()
        ));
        if (main.connected) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    main.client.send(json);
                }
            }.runTaskAsynchronously(main.main);
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        String json = utils.toJSON(Map.of(
                "method", "kick",
                "username", player.getName(),
                "uuid", player.getUniqueId().toString(),
                "ip", player.getAddress().getHostString()
        ));
        if (main.connected) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    main.client.send(json);
                }
            }.runTaskAsynchronously(main.main);
        }
    }

    @EventHandler
    public void onPlayerReSpawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        String json = utils.toJSON(Map.of(
                "method", "respawn",
                "username", player.getName(),
                "uuid", player.getUniqueId().toString(),
                "ip", player.getAddress().getHostString()
        ));
        if (main.connected) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    main.client.send(json);
                }
            }.runTaskAsynchronously(main.main);
        }
    }

    @EventHandler
    public void onPlayerDead(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String json = utils.toJSON(Map.of(
                "method", "dead",
                "message", event.getDeathMessage(),
                "username", player.getName(),
                "uuid", player.getUniqueId().toString(),
                "ip", player.getAddress().getHostString()
        ));
        if (main.connected) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    main.client.send(json);
                }
            }.runTaskAsynchronously(main.main);
        }
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        String json = utils.toJSON(Map.of(
                "method", "chat",
                "username", player.getName(),
                "world", player.getWorld().getName(),
                "display", player.getDisplayName(),
                "uuid", player.getUniqueId().toString(),
                "ip", player.getAddress().getHostString(),
                "message", event.getMessage()
        ));
        if (main.connected) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    main.client.send(json);
                }
            }.runTaskAsynchronously(main.main);
        }
    }
}
