package me.kavzaq.qEssentialsReloaded.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SerializeUtils {
    
    private static final StringBuilder strb = new StringBuilder();
    private SerializeUtils() { }
    
    public static String serializeLocation(Location location) {
        strb.setLength(0);
        strb.append(location.getWorld().getName() + " ");
        strb.append(location.getX() + " ");
        strb.append(location.getY() + " ");
        strb.append(location.getZ() + " ");
        strb.append(location.getYaw() + " ");
        strb.append(location.getPitch());
        return strb.toString();
    }
    
    public static Location deserializeLocation(String parsedLoc) {
        String[] split = parsedLoc.split(" ");
        World world = Bukkit.getServer().getWorld(split[0]);
        if (world == null) {
            return null;
        }
        return new Location(world, 
                Double.valueOf(split[1]), Double.valueOf(split[2]), Double.valueOf(split[3]), Float.valueOf(split[4]), Float.valueOf(split[5]));
    }
    
}
