package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.WarpImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.WarpManagerImpl;
import me.kavzaq.qEssentialsReloaded.utils.ListingUtils;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends CommandImpl {
    
    public WarpCommand() {
        super("warp", "Teleport to a warp", "/warp [name]", "warp", Arrays.asList("qwarp"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player)s;
        if (args.length == 0) {
            Util.sendMessage(p, MessagesImpl.WARP_LIST + ListingUtils.getWarpList());
            return;
        } else if (args.length == 1) {
            String warpName = args[0];
            if (!WarpManagerImpl.exists(warpName)) {
                Util.sendMessage(p, MessagesImpl.WARP_UNKNOWN);
                return;
            }
            if ((!p.hasPermission("qessentials.warps.*") || (!p.hasPermission("qessentials.warps." + warpName)))) {
                Util.sendMessage(p, MessagesImpl.NO_PERMISSION
                        .replace("%permission%", "qessentials.warps." + warpName + " / " + "qessentials.warps.*"));
                return;
            }
            TeleportUtils tpu = new TeleportUtils(p);
            WarpImpl warp = WarpManagerImpl.getWarp(warpName);
            tpu.teleport(warp.getLocation(), true);
            Util.sendMessage(p, MessagesImpl.WARP_SUCCESS
                    .replace("%warp%", warpName));
            return;
        }
        Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
    }
    
}
