package me.kavzaq.qEssentialsReloaded.listeners;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.ChatManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.switches.ChatSwitch;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (!ChatSwitch.getChat()) {
            if (!p.hasPermission("qessentials.chat.bypass")) {
                e.setCancelled(true);
                Util.sendMessage(p, MessagesImpl.CHAT_IS_DISABLED);
                return;
            }
        }
        String format = null;
        String defaultFormat = format = Main.getInstance().getConfig().getString("chat-format");

        if (!Main.chat_support) {
            format = defaultFormat;
        } else if ((Main.chat.getPrimaryGroup(p) == null) || (ChatManagerImpl.getGroups().get(Main.chat.getPrimaryGroup(p)) == null)) {
            format = defaultFormat;
            format = StringUtils.replace(format, "{SUFFIX}", Main.chat.getPlayerSuffix(p));
            format = StringUtils.replace(format, "{PREFIX}", Main.chat.getPlayerPrefix(p));
        } else if (!ChatManagerImpl.getGroups().get(Main.chat.getPrimaryGroup(p)).isEmpty()) {
            String primaryGroup = Main.chat.getPrimaryGroup(p);
            format = ChatManagerImpl.getGroups().get(primaryGroup);
            format = StringUtils.replace(format, "{SUFFIX}", Main.chat.getPlayerSuffix(p));
            format = StringUtils.replace(format, "{PREFIX}", Main.chat.getPlayerPrefix(p));
        } else {
            format = defaultFormat;
        }
        format = StringUtils.replace(format, "{PLAYER}", p.getName());
        format = StringUtils.replace(format, "{DISPLAYNAME}", p.getDisplayName());
        format = StringUtils.replace(format, "{HEALTH}", String.valueOf(p.getHealth() / 2));
        format = StringUtils.replace(format, "{FOOD}", String.valueOf(p.getFoodLevel() / 2));
        format = StringUtils.replace(format, "{MESSAGE}", e.getMessage());
        e.setFormat(Util.fixColors(format));

        int _slowdownTime = Main.getInstance().getConfig().getInt("chat-slowdown");
        int _slowdownTimeMS = _slowdownTime * 1000;


    }

}
