package me.kavzaq.qEssentialsReloaded.io.caches;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.SerializeUtils;
import org.bukkit.Location;

public class CacheManager {
    
    private CacheManager() { }
    
    public static void setSpawnLocation(Location location) {
        CacheFile.getFileConfiguration().set("SPAWN_LOCATION", SerializeUtils.serializeLocation(location));
        CacheFile.save();
       
        Main.Debug(CacheFile.getFileConfiguration().getString("SPAWN_LOCATION"));
    }
    
    public static Location getSpawnLocation() {
        String spawnLocationUnparsed = CacheFile.getFileConfiguration().getString("SPAWN_LOCATION");
        return SerializeUtils.deserializeLocation(spawnLocationUnparsed);
    }
    
}
