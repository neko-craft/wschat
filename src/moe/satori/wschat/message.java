package moe.satori.wschat;

import org.bukkit.Bukkit;

public class message {
    public static void receive(String message) {
        Bukkit.getLogger().info("Got Server Message: " + message);
        //Do Something with server response
    }
}