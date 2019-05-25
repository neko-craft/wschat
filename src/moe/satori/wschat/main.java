package moe.satori.wschat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.net.URI;


public class main extends JavaPlugin {
    public static Plugin main;
    public static String ws_server_url;
    public static websocket client;
    public static int retry = 0;
    public static boolean connected = false;
    public static int max_retry;
    public static int retry_time;

    public static void connect() {
        new BukkitRunnable() {
            public void run() {
                try {
                    client = new websocket( new URI(ws_server_url));
                    Bukkit.getLogger().info("Connectig to WebSocket Server: " + ws_server_url);
                    client.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(main);
    }
    @Override
    public void onEnable() {
        main = this;
        saveDefaultConfig();
        this.setConfig();
        if (ws_server_url != "") {
            connect();
            Bukkit.getLogger().info("Start Listening Chat Events");
            this.getServer().getPluginManager().registerEvents(new events(), this);
        } else {
            Bukkit.getLogger().info("WebSocket Server URL Missing");
        }
    }
    private void setConfig() {
        reloadConfig();
        ws_server_url = this.getConfig().getString("websocket_server");
        max_retry = this.getConfig().getInt("retry_connect");
        retry_time = this.getConfig().getInt("retry_time");
    }
    @Override
    public void onDisable() {

    }
}
