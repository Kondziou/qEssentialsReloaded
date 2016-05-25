package me.kavzaq.qEssentialsReloaded.listeners;


import me.kavzaq.qEssentialsReloaded.impl.BanImpl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.BanManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.timed.TemporaryBanTimed;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerLoginListener implements Listener {
    
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerLogin(PlayerLoginEvent e) {
        OfflinePlayer offPlayer = Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId());
        BanImpl ban = BanManagerImpl.getBan(offPlayer);
        
        if (TemporaryBanTimed.isBlocked(offPlayer.getUniqueId())) {
            e.disallow(Result.KICK_BANNED, Util.fixColors(MessagesImpl.KICK_TEMPBAN
                    .replace("%cooldown%", TemporaryBanTimed.timeRemain(offPlayer.getUniqueId()))));
        }
        
        if (ban == null) return;
        if (!BanManagerImpl.isPardonned(offPlayer)) {
            String till = ban.getTill() == 0 ? MessagesImpl.BAN_PERMAMENT : 
                    Util.parseTime(((ban.getTill() - System.currentTimeMillis())) + 1);
            String format = Util.listToString(MessagesImpl.BAN_FORMAT);
            e.disallow(Result.KICK_BANNED, format
                .replace("%player%", ban.getPunisher())
                .replace("%reason%", Util.fixColors(ban.getReason()))
                .replace("%till%", till));
            return;
        }
        else {
            BanManagerImpl.deleteBan(ban);
        }
       
    }

}
