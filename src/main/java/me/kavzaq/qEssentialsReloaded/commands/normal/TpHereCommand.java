package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TpHereCommand extends CommandImpl {

    public TpHereCommand() {
        super("tphere", "Teleporting someone to player", "/tphere <player>", "tphere", Arrays.asList("tph", "qtphere", "s"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player) s;
        if ((args.length == 0) || (args.length >= 2)) {
            Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            Util.sendMessage(p, MessagesImpl.OFFLINE_PLAYER);
            return;
        }
        Player other = Bukkit.getPlayer(args[0]);
        TeleportUtils tpu = new TeleportUtils(other);
        tpu.teleport(p.getLocation(), true);
        Util.sendMessage(p, MessagesImpl.TPHERE_SUCCESS
                .replace("%player%", other.getName()));
    }

}
