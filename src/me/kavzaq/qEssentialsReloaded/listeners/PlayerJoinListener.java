package me.kavzaq.qEssentialsReloaded.listeners;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.TabConfigurationImpl;
import me.kavzaq.qEssentialsReloaded.impl.UpdaterImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.io.input.SynchronizedInput;
import me.kavzaq.qEssentialsReloaded.utils.ReplaceUtils;
import me.kavzaq.qEssentialsReloaded.utils.TablistUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;

public class PlayerJoinListener implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {
            @Override
            public void run() { 
                try {   
                    SynchronizedInput si = new SynchronizedInput("motd.txt");
                    List<String> list = si.getLines();
            
                    for (String s : list) {
                        Util.sendMessage(p, ReplaceUtils.replaceVariables(p, s));
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return;
                }       
            }
        });
        
        UserImpl u = Main.getUserManager().getUser(p);
        
        String joinMessage = Main.getInstance().getConfig().getString("join-format");
        joinMessage = StringUtils.replace(joinMessage, "{PLAYER}", e.getPlayer().getName());
        joinMessage = StringUtils.replace(joinMessage, "{DISPLAYNAME}", e.getPlayer().getDisplayName());
        e.setJoinMessage(Util.fixColors(joinMessage));
        if (u == null) {
            u = Main.getUserManager().implementUser(p);
        }
           
        UpdaterImpl.checkUpdate();
        if (!UpdaterImpl.isUpdated()) {
            if (!p.hasPermission("qessentials.updater")) {
                return;
            }
            Util.sendMessage(p, "&aNew version of qEssentialsReloaded is available!");
            Util.sendMessage(p, "&a  Newest version: &l" + UpdaterImpl.getNewestVersion());
            Util.sendMessage(p, "&a  Current version: &l" + UpdaterImpl.getCurrentVersion());
            Util.sendMessage(p, "&aPlease update it on &lhttp://github.com/xVacuum/qEssentialsReloaded/releases");
            Util.sendMessage(p, "&aIt's important.");
        }
        
        if (!TabConfigurationImpl.tablistEnabled) {
            return;
        }
        
        TablistUtils.showTab(p);
    }
}
