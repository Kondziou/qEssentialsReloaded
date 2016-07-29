package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.WarpManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SetWarpCommand extends CommandImpl {

    public SetWarpCommand() {
        super("setwarp", "Sets a new warp", "/setwarp <name>", "setwarp", Arrays.asList("qsetwarp"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player) s;
        if ((args.length == 0) || (args.length >= 2)) {
            Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        String warpName = args[0];
        if (WarpManagerImpl.exists(warpName)) {
            Util.sendMessage(p, MessagesImpl.SETWARP_EXISTS);
            return;
        }
        if (!StringUtils.isAlpha(warpName)) {
            Util.sendMessage(p, MessagesImpl.SETWARP_INVALID);
            return;
        }
        WarpManagerImpl.addWarp(warpName, p.getLocation());
        Util.sendMessage(p, MessagesImpl.SETWARP_SUCCESS
                .replace("%warp%", warpName));
    }

}
