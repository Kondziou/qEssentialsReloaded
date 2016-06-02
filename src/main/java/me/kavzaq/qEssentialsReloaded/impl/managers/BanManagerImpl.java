package me.kavzaq.qEssentialsReloaded.impl.managers;

import com.google.common.collect.Lists;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.BanImpl;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BanManagerImpl {
    
    private BanManagerImpl() { }
    private static final List<BanImpl> bans = Lists.newArrayList();
    
    public static List<BanImpl> getBans() {
        return bans;
    }
    
    public static void loadBans() {
        try {
            PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                    "SELECT * FROM bans");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                OfflinePlayer offPunished = Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("punished")));
                BanImpl ban = new BanImpl(offPunished, rs.getString("punisher"));
                ban.setReason(rs.getString("reason"));
                ban.setTill(rs.getLong("till"));
                bans.add(ban);
            }
        } catch (SQLException e) {
            Main.log.send(e);
        }
    }
    
    public static void addBan(BanImpl ban) {
        try {
            PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                "INSERT INTO `bans` (`punisher`, `punished`, `till`, `reason`) VALUES (?, ?, ?, ?)");
            stat.setString(1, ban.getPunisher());
            stat.setString(2, ban.getPunished().toString());
            stat.setLong(3, ban.getTill());
            stat.setString(4, ban.getReason());
            stat.executeUpdate();
            bans.add(ban);
        } catch (Exception e) {
            Main.log.send(e);
        }
    }
    
    public static void deleteBan(BanImpl ban) {
        try {
            PreparedStatement stat = Main.getDb().getConnection().prepareStatement(
                "DELETE FROM bans WHERE `punished`=?");
            stat.setString(1, ban.getPunished().toString());
            stat.executeUpdate();
            bans.remove(ban);
        } catch (Exception e) {
            Main.log.send(e);
        }
    }
    
    public static boolean isPardonned(OfflinePlayer player) {
        BanImpl ban = getBan(player);
        if (ban == null) return true;
        return ban.getTill() != 0L && ban.getTill() <= System.currentTimeMillis();
    }
    
    public static BanImpl getBan(OfflinePlayer player) {
        for (BanImpl ban : bans) {
            if (ban.getPunished().equals(player.getUniqueId())) return ban;
        }
        return null;
    }
    
    public static boolean isBanned(Player player) {
        BanImpl ban = getBan(player);
        if (ban == null) return false;
        return ban.getTill() != 0L && ban.getTill() <= System.currentTimeMillis();
    }
    
    public static BanImpl getBan(Player player) {
        for (BanImpl ban : bans) {
            if (ban.getPunished().equals(player.getUniqueId())) return ban;
        }
        return null;
    }
    
}
