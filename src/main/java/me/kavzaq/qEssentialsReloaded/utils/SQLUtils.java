package me.kavzaq.qEssentialsReloaded.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.data.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.data.KitDataImpl;

public class SQLUtils {
    
    private SQLUtils() { }
    
    public static boolean isKitNull(KitDataImpl kitData) {
        try (PreparedStatement stat = Main.getDb().getConnection()
                .prepareStatement("SELECT * FROM kits WHERE `name`=?")) {
            stat.setString(1, kitData.getName());
            
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                return rs.getString("cooldown") == null;
            }
        } catch (SQLException e) {
            Main.log.send(e);
        }
        return true;
    }
    
    public static boolean isHomeNull(HomeDataImpl homeData) {
        try (PreparedStatement stat = Main.getDb().getConnection()
                .prepareStatement("SELECT * FROM homes WHERE `name`=?")) {
            stat.setString(1, homeData.getName());
            
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                return rs.getString("world") == null;
            }
            stat.close();
        } catch (SQLException e) {
            Main.log.send(e);
        }
        return true;
    }
    
}
