package me.kavzaq.qEssentialsReloaded.impl;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.impl.data.KitDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.data.HomeDataImpl;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.SQLUtils;

import org.bukkit.entity.Player;

public class UserImpl {

    private final String name;
    private final UUID uuid;
    private boolean god;
    private List<HomeDataImpl> homes = Lists.newArrayList();
    private List<KitDataImpl> kits = Lists.newArrayList();
    
    private static boolean changed = false;
    
    public UserImpl(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
    }
    
    public UserImpl(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }
    
    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public boolean isGod() {
        return god;
    }
    
    public List<KitDataImpl> getKits() {
        return kits;
    }

    public void addKit(KitDataImpl kitData) {
        this.kits.add(kitData);
        changed = true;
        save();
    }

    public void delKit(KitDataImpl kitData) {
        this.kits.remove(kitData);
        changed = true;
        deleteKit(kitData);
    }

    public void setKits(List<KitDataImpl> kits) {
        this.kits = kits;
        changed = true;
        save();
    }
    
    public void setGod(boolean god) {
        this.god = god;
    }

    public List<HomeDataImpl> getHomes() {
        return homes;
    }
    
    public void setHomes(List<HomeDataImpl> homes) {
        this.homes = homes;
        changed = true;
        save();
    } 
    
    public void addHome(HomeDataImpl homeData) {
        this.homes.add(homeData);
        changed = true;
        save();
    }
    

    public void delHome(HomeDataImpl homeData) {
        this.homes.remove(homeData);
        changed = true;
        deleteHome(homeData); 
    }
    
    public void save() {
        if (!changed) return;
        for (KitDataImpl kitData : this.getKits()) {
            if (SQLUtils.isKitNull(kitData)) implementKit(kitData);
            else saveKit(kitData);
        }
        for (HomeDataImpl homeData : this.getHomes()) {
            if (SQLUtils.isHomeNull(homeData)) implementHome(homeData);
            else saveHome(homeData);
        }
    }
    
    public void implementKit(KitDataImpl kitData) {
        try {
            try (PreparedStatement stat = Main.getSQLite().getConnection().prepareStatement(
                    "INSERT INTO `kits` (`uuid`, `name`, `cooldown`) VALUES (?, ?, ?)")) {
                stat.setString(1, this.uuid.toString());
                stat.setString(2, kitData.getName());
                stat.setLong(3, kitData.getCooldown());
                stat.executeUpdate();
                stat.close();
            }
        } catch (Exception e) {
            Main.log.send(e);
        }
    }
    
    public void implementHome(HomeDataImpl homeData) {
        try {
            try (PreparedStatement stat = Main.getSQLite().getConnection().prepareStatement(
                    "INSERT INTO `homes` (`uuid`, `name`, `world`, `x`, `y`, `z`, `pitch`, `yaw`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                stat.setString(1, this.uuid.toString());
                stat.setString(2, homeData.getName());
                stat.setString(3, homeData.getLocation().getWorld().getName());
                stat.setFloat(4, (float)homeData.getLocation().getX());
                stat.setFloat(5, (float)homeData.getLocation().getY());
                stat.setFloat(6, (float)homeData.getLocation().getZ());
                stat.setFloat(7, homeData.getLocation().getYaw());
                stat.setFloat(8, homeData.getLocation().getPitch());
                stat.executeUpdate();
                stat.close();
            }
        } catch (Exception e) {
            Main.log.send(e);
        }
    }
    
    public void saveKit(KitDataImpl kitData) {
        try {
            try (PreparedStatement stat = Main.getSQLite().getConnection().prepareStatement(
                "UPDATE `kits` SET `name`=?,`cooldown`=? WHERE `uuid`=? AND `name`=?")) {
                stat.setString(1, kitData.getName());
                stat.setLong(2, kitData.getCooldown());
                stat.setString(3, this.uuid.toString());
                stat.setString(4, kitData.getName());
                stat.executeUpdate();
                stat.close();
            }
        } catch (Exception e) {
            Main.log.send(e);
        }
    }
    
    public void saveHome(HomeDataImpl homeData) {
        try {
            try (PreparedStatement stat = Main.getSQLite().getConnection().prepareStatement(
                "UPDATE `homes` SET `name`=?,`world`=?,`x`=?,`y`=?,`z`=?,`pitch`=?,`yaw`=? WHERE `uuid`=? AND `name`=?")) {
                stat.setString(1, homeData.getName());
                stat.setString(2, homeData.getLocation().getWorld().getName());
                stat.setFloat(3, (float)homeData.getLocation().getX());
                stat.setFloat(4, (float)homeData.getLocation().getY());
                stat.setFloat(5, (float)homeData.getLocation().getZ());
                stat.setFloat(6, homeData.getLocation().getYaw());
                stat.setFloat(7, homeData.getLocation().getPitch());
                stat.setString(8, this.uuid.toString());
                stat.setString(9, homeData.getName());
                stat.executeUpdate();
                stat.close();
            }
        } catch (Exception e) {
            Main.log.send(e);
        }
    }
    
    public void deleteHome(HomeDataImpl homeData) {
        try {
            PreparedStatement stat = Main.getSQLite().getConnection().prepareStatement(
                "DELETE FROM `homes` WHERE `uuid`=? AND `name`=?");
            stat.setString(1, this.uuid.toString());
            stat.setString(2, homeData.getName());
            stat.executeUpdate();
            stat.close();
        } catch (SQLException e) {
            Main.log.send(e);
        }
    }
    
    public void deleteKit(KitDataImpl kitData) {
        try {
            PreparedStatement stat = Main.getSQLite().getConnection().prepareStatement(
                "DELETE FROM `kits` WHERE `uuid`=? AND `name`=?");
            stat.setString(1, this.uuid.toString());
            stat.setString(2, kitData.getName());
            stat.executeUpdate();
            stat.close();
        } catch (SQLException e) {
            Main.log.send(e);
        }
    }
}
