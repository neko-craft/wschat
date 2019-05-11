package moe.satori.wschat;


import java.net.URI;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class websocket extends WebSocketClient {

    public websocket( URI serverUri , Draft draft ) {
        super( serverUri, draft );
    }

    public websocket( URI serverURI ) {
        super( serverURI );
    }

    public websocket( URI serverUri, Map<String, String> httpHeaders ) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        main.retry = 0;
        main.connected = true;
        Bukkit.getLogger().info("WebSocket Server Connected");
    }

    @Override
    public void onMessage( String msg ) {
        message.receive(msg);
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        main.connected = false;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!main.connected) {
                    if (main.max_retry > main.retry) {
                        main.retry++;
                        try {
                            Thread.sleep(main.retry_time);
                            Bukkit.getLogger().info("Retrying [" + main.retry + "/" + main.max_retry +"] Connect Server");
                            main.connect();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }.runTaskAsynchronously(main.main);
        Bukkit.getLogger().info( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code);
    }

    @Override
    public void onError( Exception e ) {
        e.printStackTrace();
    }

}