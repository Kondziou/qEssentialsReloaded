package me.kavzaq.qEssentialsReloaded.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;
import me.kavzaq.qEssentialsReloaded.Main;

import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.database.SQLite;
import me.kavzaq.qEssentialsReloaded.utils.SerializeUtils;

public class UserImpl {

    private final String name;
    private final UUID uuid;
    private boolean god;
    private List<String> homes;
    private List<String> kits;
    
    private boolean changed = false;
    
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
    
    public List<String> getKits() {
        return kits;
    }

    public void addKit(KitDataImpl kitData) {
        this.kits.add(SerializeUtils.serializeKit(kitData));
        changed = true;
    }

    public void delKit(KitDataImpl kitData) {
        this.kits.remove(SerializeUtils.serializeKit(kitData));
        changed = true;
    }

    public void setKits(List<String> kits) {
        this.kits = kits;
        changed = true;
    }
    
    public void setGod(boolean god) {
        this.god = god;
    }

    public List<String> getHomes() {
        return homes;
    }
    
    public void setHomes(List<String> homes) {
        this.homes = homes;
        changed = true;
    }
    
    public void addHome(HomeDataImpl homeData) {
        this.homes.add(SerializeUtils.serializeHome(homeData));
        changed = true;
    }
    

    public void delHome(HomeDataImpl homeData) {
        this.homes.remove(SerializeUtils.serializeHome(homeData));
        changed = true;
    }

    public void save() {
        if (!changed) return;
        PreparedStatement stat = null;
        try {
            stat = Main.getSQLite().getConnection().prepareStatement("UPDATE `users` SET `homes`=?,`kits`=? WHERE `uuid`=?");
            stat.setString(1, SerializeUtils.serializeList(this.getHomes()));
            stat.setString(2, SerializeUtils.serializeList(this.getKits()));
            stat.setString(3, this.getUUID().toString());
            Main.getSQLite().executeUpdate(stat);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        changed = false;
    }

    public boolean isChanged() {
        return changed;
    }
}
