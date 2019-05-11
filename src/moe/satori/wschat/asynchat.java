package moe.satori.wschat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class asynchat implements Listener {

    @EventHandler
    public void onAPC(AsyncPlayerChatEvent event) {
        HashMap<String, Object> result = new HashMap<>();
        Player player = event.getPlayer();
        result.put("username", player.getName());
        result.put("world", player.getWorld().getName());
        result.put("display", player.getDisplayName());
        result.put("uuid", player.getUniqueId().toString());
        result.put("ip", player.getAddress().getHostString());
        result.put("message", event.getMessage());
        String json = utils.toJSON(result);
        if (main.connected) {
            main.client.send(json);
        }
    }
}
