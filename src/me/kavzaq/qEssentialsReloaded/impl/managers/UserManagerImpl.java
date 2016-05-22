package me.kavzaq.qEssentialsReloaded.impl.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.Main;

import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.impl.data.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.data.KitDataImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class UserManagerImpl {

    private final List<UserImpl> users = Lists.newArrayList();
    
    public List<UserImpl> getUsers() {
        return users;
    }
    
    public UserImpl loadUser(Player player) {
        UserImpl user = getUser(player);
        PreparedStatement stat = null;
        try {
            PreparedStatement kitStat = Main.getSQLite().getConnection().prepareStatement("SELECT * FROM kits WHERE uuid=?");
            kitStat.setString(1, player.getUniqueId().toString());

            PreparedStatement homeStat = Main.getSQLite().getConnection().prepareStatement("SELECT * FROM homes WHERE uuid=?");
            homeStat.setString(1, player.getUniqueId().toString());

            ResultSet kitRs = kitStat.executeQuery();
            ResultSet homeRs = homeStat.executeQuery();
            // kit/home relation to user
            while (kitRs.next()) {
                user.addKit(new KitDataImpl(kitRs.getString("name"), kitRs.getLong("cooldown")));
            }
            while (homeRs.next()) {
                user.addHome(new HomeDataImpl
                    (homeRs.getString("name"), new Location(
                            Bukkit.getWorld(homeRs.getString("world")), 
                            homeRs.getDouble("x"),
                            homeRs.getDouble("y"),
                            homeRs.getDouble("z"),
                            homeRs.getFloat("yaw"),
                            homeRs.getFloat("pitch"))));
            }
            if (!users.contains(user)) {
                users.add(user);
            }
            return user;
        } catch (SQLException sqle) {
            Main.log.send(sqle);
        }
        return getUser(player);
    }
    
    public UserImpl getUser(Player player) {
        for (UserImpl user : users) {
            if (user.getUUID().equals(player.getUniqueId())) return user;
        }
        return new UserImpl(player.getName(), player.getUniqueId());
    }
     
    public int availableHomes(Player player) {
        String homePerm = null;
        if ((player.hasPermission("'*'")) || (player.isOp())) return -1;
        for (PermissionAttachmentInfo pai : player.getEffectivePermissions()) {
            String perm = pai.getPermission();
            if (perm.contains("qessentials.home.limit.")) homePerm = perm;
        }
        if (homePerm == null) {
            return Main.getInstance().getConfig().getInt("max-homes");
        }
        String[] split = homePerm.split("\\.");
        int availableHomes = Integer.valueOf(split[3]);
        return availableHomes;
    }

}
