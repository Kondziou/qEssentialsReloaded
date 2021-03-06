package me.kavzaq.qEssentialsReloaded.impl.teleport;

import com.google.common.collect.Maps;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportUpdaterImpl {

    private static final HashMap<Player, Location> locations = Maps.newHashMap();

    public Location getLocation(Player player) {
        return locations.get(player) != null ?
                locations.get(player) : player.getWorld().getSpawnLocation();
    }

    public void setLocation(Player player) {
        locations.put(player, player.getLocation());
        return;
    }

}
