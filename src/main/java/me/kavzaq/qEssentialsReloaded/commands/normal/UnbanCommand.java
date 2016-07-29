package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.BanImpl;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.BanManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class UnbanCommand extends CommandImpl {

    public UnbanCommand() {
        super("unban", "Pardons player", "/unban <player>", "unban", Arrays.asList("qunban", "pardon"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if ((args.length == 0) || (args.length >= 2)) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        OfflinePlayer other = Bukkit.getOfflinePlayer(args[0]);

        if (BanManagerImpl.isPardonned(other)) {
            Util.sendMessage(s, MessagesImpl.UNBAN_UNKNOWN);
            return;
        }

        BanImpl ban = BanManagerImpl.getBan(other);
        BanManagerImpl.deleteBan(ban);

        Util.sendMessage(s, MessagesImpl.UNBAN_SUCCESS.replace("%player%", other.getName()));
        Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.UNBAN_BROADCAST.replace("%player%", other.getName())));
    }

}
