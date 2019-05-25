package moe.satori.wschat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;

public class message {
    public static void receive(String json) {
        Gson gson = new Gson();
        JsonObject message = new JsonParser().parse(json).getAsJsonObject();
        String method = message.get("method").getAsString();
        if (method == null) {
            return;
        }
        switch (method) {
            case "chat":
                String username = message.get("username").getAsString();
                String msg = message.get("message").getAsString();
                ComponentBuilder send_msg = new ComponentBuilder("[ChatTransfer]: ").
                        color(ChatColor.GREEN).
                        append("<" + username + ">: ").
                        color(ChatColor.BLUE).
                        append(msg).color(ChatColor.WHITE);

                Bukkit.spigot().broadcast(send_msg.create());
                break;
        }
        Bukkit.getLogger().info("Got Server Message: " + message);
        //Do Something with server response
    }
}