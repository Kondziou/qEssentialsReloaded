package me.kavzaq.qEssentialsReloaded.utils.switches;

import org.bukkit.entity.Player;

public class FlySwitch {
    
    private FlySwitch() { }
    
    public static boolean switchFlying(Player player) {
        return !player.getAllowFlight();
    }

}
