package me.kavzaq.qEssentialsReloaded.utils;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.mysql.jdbc.StringUtils;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.data.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.impl.WarpImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.WarpManagerImpl;

public class ListingUtils {
    
    private static final StringBuilder localsb = new StringBuilder();
    private ListingUtils() { }
    
    public static String getHomeList(Player player) {
        localsb.setLength(0);
        UserImpl user = Main.getUserManager().getUser(player);
        for (HomeDataImpl home : user.getHomes()) { 
            if (!StringUtils.isNullOrEmpty(home.getName())) {
                localsb.append(MessagesImpl.WHOIS_HOMES_INDEX
                        .replace("%home%", home.getName())
                        .replace("%location%", 
                                "x" + home.getLocation().getX() +
                                ", y" + home.getLocation().getY() +
                                ", z" + home.getLocation().getZ())
                        + "\n");
            }
        }
        return localsb.toString();
    }
    
    public static String getWorldList() {
        localsb.setLength(0);
        for (World w : Bukkit.getWorlds()) {
            int tiles = 0;
            Chunk[] loadedChunks = w.getLoadedChunks();
            for (Chunk c : loadedChunks) {
                tiles += c.getTileEntities().length;
            }
            localsb.append(MessagesImpl.GARBAGECOLLECTOR_WORLD_FORMAT
                        .replace("%world%", w.getName())
                        .replace("%objects%", String.valueOf(w.getEntities().size()))
                        .replace("%chunks%", String.valueOf(loadedChunks.length))
                        .replace("%tiles%", String.valueOf(tiles))
                    + "\n");
        }
        return localsb.toString();
    }
    
    public static String getWarpList() {
        localsb.setLength(0);
        for (WarpImpl warp : WarpManagerImpl.getWarps()){
            localsb.append(", " + warp.getName());
        }
        if (localsb.length() == 0) {
            localsb.append("*none*");
        }
        return localsb.toString().replaceFirst(", ", "");
    }

}
