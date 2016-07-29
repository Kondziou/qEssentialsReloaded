package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.BanImpl;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.BanManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.TimeUnit;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TempBanCommand extends CommandImpl {

    public TempBanCommand() {
        super("tempban", "Temporary bans a player", "/tempban <player> <till (sec/min/hour/day/week/month/year)> [reason]",
                "tempban", Arrays.asList("qtempban", "temppunish"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length <= 1) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        String reason = null;
        if (args.length == 2) {
            reason = Util.fixColors(MessagesImpl.BAN_NO_REASON);
        } else if (args.length >= 3) {
            reason = Util.fixColors(StringUtils.join(args, " ", 2, args.length));
        }
        String format = Util.listToString(MessagesImpl.BAN_FORMAT);
        long till = 0;
        try {
            till = TimeUnit.parseUnit(args[1]);
        } catch (Exception e) {
        }
        OfflinePlayer other = Bukkit.getOfflinePlayer(args[0]);

        // unbanning if banned previously
        if (!BanManagerImpl.isPardonned(other)) {
            BanImpl ban = BanManagerImpl.getBan(other);
            BanManagerImpl.deleteBan(ban);
        }

        BanImpl ban = new BanImpl(other, s.getName());
        ban.setReason(reason);
        ban.setTill(till != 0 ? System.currentTimeMillis() + till : 0);
        BanManagerImpl.addBan(ban);

        if (other.isOnline()) {
            Player onlineOther = Bukkit.getPlayer(other.getUniqueId());
            onlineOther.kickPlayer(format
                    .replace("%player%", s.getName())
                    .replace("%reason%", reason)
                    .replace("%till%", till != 0 ? Util.parseTime(till) : MessagesImpl.BAN_PERMAMENT));
        }
        Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.TEMPBAN_BROADCAST
                .replace("%player%", other.getName())
                .replace("%reason%", reason))
                .replace("%till%", till != 0 ? Util.parseTime(till) : MessagesImpl.BAN_PERMAMENT));
        Util.sendMessage(s, MessagesImpl.TEMPBAN_SUCCESS
                .replace("%player%", other.getName()));
    }

}
