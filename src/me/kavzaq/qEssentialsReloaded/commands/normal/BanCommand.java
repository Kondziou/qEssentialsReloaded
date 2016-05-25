package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import me.kavzaq.qEssentialsReloaded.impl.BanImpl;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.BanManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand extends CommandImpl {

    public BanCommand() {
        super("ban", "Bans a player", "/ban <player> [reason]", "ban", Arrays.asList("qban", "punish"));
    }
    
    private static final StringBuilder localsb = new StringBuilder();

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length == 0) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        String reason = null;
        if (args.length == 1) {
            reason = Util.fixColors(MessagesImpl.BAN_NO_REASON);
        } 
        else if (args.length > 1) {
            reason = Util.fixColors(StringUtils.join(args, " ", 1, args.length));
        }
        
        String format = Util.listToString(MessagesImpl.BAN_FORMAT);
        OfflinePlayer other = Bukkit.getOfflinePlayer(args[0]);
        
        // unbanning if banned previously
        if (!BanManagerImpl.isPardonned(other)) {
            BanImpl ban = BanManagerImpl.getBan(other);
            BanManagerImpl.deleteBan(ban);
        }
        
        BanImpl ban = new BanImpl(other, s.getName());
        ban.setReason(reason);
        ban.setTill(0);
        BanManagerImpl.addBan(ban);
        
        
        if (other.isOnline()) {
            Player onlineOther = Bukkit.getPlayer(other.getUniqueId());
            onlineOther.kickPlayer(format
                .replace("%player%", s.getName())
                .replace("%reason%", reason)
                .replace("%till%", MessagesImpl.BAN_PERMAMENT));
        }
        Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.BAN_BROADCAST
                .replace("%player%", other.getName())
                .replace("%reason%", reason)));
        Util.sendMessage(s, MessagesImpl.BAN_SUCCESS
                .replace("%player%", other.getName()));
    }
    
}
