package me.kavzaq.qEssentialsReloaded.impl.managers;

import com.google.common.collect.Lists;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.WarpImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class WarpManagerImpl {
    
    private WarpManagerImpl() { }
    private static final List<WarpImpl> warps = Lists.newArrayList();
    
    public static List<WarpImpl> getWarps() {
        return warps;
    }
    
    public static void loadWarps() {
        try {
            PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                    "SELECT * FROM warps");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                WarpImpl warp = new WarpImpl(
                        rs.getString("name"),
                        new Location(
                            Bukkit.getWorld(rs.getString("world")), 
                            rs.getDouble("x"),
                            rs.getDouble("y"),
                            rs.getDouble("z"),
                            rs.getFloat("yaw"),
                            rs.getFloat("pitch")));
                warps.add(warp);
            }
        } catch (SQLException e) {
            Main.log.send(e);
        }
    }
    
    public static void delWarp(String name) {
        try {
            try (PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                    "DELETE FROM `warps` WHERE `name`=?")) {
                stat.setString(1, name);
                stat.executeUpdate();
            }
            PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                "DELETE FROM `warps` WHERE `name`=?");
            stat.setString(1, name);
            stat.executeUpdate();
            stat.close();
            WarpImpl toDelete = getWarp(name);
            warps.remove(toDelete);
        } catch (SQLException e) {
            Main.log.send(e);
        }
    }
    
    public static void addWarp(String name, Location location) {
        WarpImpl warp = new WarpImpl(name, location);
        try {
            try (PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                    "INSERT INTO `warps` (`name`, `world`, `x`, `y`, `z`, `pitch`, `yaw`) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                stat.setString(1, warp.getName());
                stat.setString(2, warp.getLocation().getWorld().getName());
                stat.setFloat(3, (float)warp.getLocation().getX());
                stat.setFloat(4, (float)warp.getLocation().getY());
                stat.setFloat(5, (float)warp.getLocation().getZ());
                stat.setFloat(6, warp.getLocation().getYaw());
                stat.setFloat(7, warp.getLocation().getPitch());
                stat.executeUpdate();
            }
            PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                "INSERT INTO `warps` (`name`, `world`, `x`, `y`, `z`, `pitch`, `yaw`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stat.setString(1, warp.getName());
            stat.setString(2, warp.getLocation().getWorld().getName());
            stat.setFloat(3, (float)warp.getLocation().getX());
            stat.setFloat(4, (float)warp.getLocation().getY());
            stat.setFloat(5, (float)warp.getLocation().getZ());
            stat.setFloat(6, warp.getLocation().getYaw());
            stat.setFloat(7, warp.getLocation().getPitch());
            stat.executeUpdate();
            stat.close();
        } catch (SQLException e) {
            Main.log.send(e);
        }
        warps.add(warp);
    }
    
    public static WarpImpl getWarp(String name) {
        WarpImpl warp = null;
        for (WarpImpl w : warps) {
            if (w.getName().equals(name)) warp = w;
        }
        return warp;
    }
    
    public static boolean exists(String name) {
        for (WarpImpl w : warps) {
            if (w.getName().equals(name)) return true;
        }
        return false;
    }
    
}
