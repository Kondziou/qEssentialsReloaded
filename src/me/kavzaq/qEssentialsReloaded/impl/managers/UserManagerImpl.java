package me.kavzaq.qEssentialsReloaded.impl.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.Main;

import me.kavzaq.qEssentialsReloaded.database.SQLite;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.SerializeUtils;

public class UserManagerImpl {

    private final List<UserImpl> users = Lists.newArrayList();
    
    public List<UserImpl> getUsers() {
        return users;
    }
    
    public UserImpl loadUser(Player player) {
        PreparedStatement stat = null;
        try {
            stat = Main.getSQLite().getConnection().prepareStatement("SELECT * FROM users WHERE uuid=?");
            stat.setString(1, player.getUniqueId().toString());
            
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                UserImpl user = new UserImpl(rs.getString("name"), 
                            UUID.fromString(rs.getString("uuid")));
                user.setGod(false);
                
                List<String> homes = SerializeUtils.deserializeList(rs.getString("homes"));
                List<String> kits = SerializeUtils.deserializeList(rs.getString("kits"));
                
                user.setHomes(homes == null ? Lists.newArrayList() : homes);
                user.setKits(kits == null ? Lists.newArrayList() : kits);
                users.add(user);
                return user;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return null;
    }
    
    public UserImpl implementUser(Player player) {
        UserImpl user = new UserImpl(player);
        user.setGod(false);
        user.setKits(Lists.newArrayList());
        user.setHomes(Lists.newArrayList());
        if ((!player.hasPlayedBefore()) || (!users.contains(player))) {
            PreparedStatement stat = null;
            try {
                stat = Main.getSQLite().getConnection().prepareStatement(
                        "INSERT INTO `users` (`id`, `name`, `uuid`, `homes`, `kits`) VALUES (NULL, ?, ?, ?, ?)");
                stat.setString(1, user.getName());
                stat.setString(2, user.getUUID().toString());
                stat.setString(3, SerializeUtils.serializeList(user.getHomes()));
                stat.setString(4, SerializeUtils.serializeList(user.getKits()));
            } catch (SQLException e) {
               e.printStackTrace();
            }
        
            Main.getSQLite().executeUpdate(stat);
            users.add(user);
        }
        else {
            user = loadUser(player);
        }
        return user;
    }
    
    public UserImpl getUser(Player player) {
        for (UserImpl user : users) {
            if (user.getUUID().equals(player.getUniqueId())) return user;
        }
        return null;
    }

    
    public UserImpl getUser(CommandSender sender) {
        return null;
    }

    
    public UserImpl getUser(UUID uuid) {
        return null;
    }


}
