package me.kavzaq.qEssentialsReloaded.listeners;

import me.kavzaq.qEssentialsReloaded.io.caches.CacheManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        e.setRespawnLocation(CacheManager.getSpawnLocation());
    }
}
