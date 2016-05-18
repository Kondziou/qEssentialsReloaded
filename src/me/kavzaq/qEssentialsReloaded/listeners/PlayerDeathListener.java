package me.kavzaq.qEssentialsReloaded.listeners;

import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        boolean disabled = Main.getInstance().getConfig().getBoolean("kill-messages-disabled");
        if (disabled) e.setDeathMessage(null);
    }
    
}
