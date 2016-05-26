package me.kavzaq.qEssentialsReloaded.impl;

import java.sql.PreparedStatement;
import java.util.UUID;
import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.OfflinePlayer;

public class BanImpl {
    
    private final String punishedName;
    private final String punisher;
    private final UUID punished;
    private String reason;
    private long till;
    
    public BanImpl(OfflinePlayer punished, String punisher) {
        this.punished = punished.getUniqueId();
        this.punisher = punisher;
        
        this.punishedName = punished.getName();
    }
    
    public String getPunishedName() {
        return punishedName;
    }
    
    public UUID getPunished() {
        return punished;
    }
    
    public String getPunisher() {
        return punisher;
    }
    
    public String getReason() {
        return reason;
    }
    
    public long getTill() {
        return till;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public void setTill(long till) {
        this.till = till;
    }
    
    public void save() {
        try {
            try (PreparedStatement stat = Main.getSQLite().getConnection().prepareStatement(
                "UPDATE `bans` SET `reason`=?,`till`=? WHERE `punished`=?")) {
                stat.setString(1, this.getReason());
                stat.setLong(2, this.getTill());
                stat.setString(3, this.getPunished().toString());
                stat.executeUpdate();
                stat.close();
            }
        } catch (Exception e) {
            Main.log.send(e);
        }
    }
    
}
