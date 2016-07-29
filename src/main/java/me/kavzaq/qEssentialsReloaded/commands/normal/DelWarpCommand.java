package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.WarpManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DelWarpCommand extends CommandImpl {

    public DelWarpCommand() {
        super("delwarp", "Deletes a warp", "/delwarp <name>", "delwarp", Arrays.asList("qdelwarp"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player) s;
        if ((args.length == 0) || (args.length >= 2)) {
            Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        String warpName = args[0];
        if (!WarpManagerImpl.exists(warpName)) {
            Util.sendMessage(p, MessagesImpl.DELWARP_UNKNOWN);
            return;
        }
        WarpManagerImpl.delWarp(warpName);
        Util.sendMessage(p, MessagesImpl.DELWARP_SUCCESS
                .replace("%warp%", warpName));
    }

}
