package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.data.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.utils.ListingUtils;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class HomeCommand extends CommandImpl {

    public HomeCommand() {
        super("home", "Teleports to a home", "/home [name]", "home", Arrays.asList("qhome", "dom"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length >= 2) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        Player p = (Player) s;
        UserImpl u = Main.getUserManager().getUser(p);

        if (args.length == 0) {
            Util.sendMessage(p, MessagesImpl.HOME_LIST_HEADER);
            Util.sendMessage(p, ListingUtils.getHomeList(p));
            return;
        } else if (args.length == 1) {
            String homeName = args[0];
            HomeDataImpl _home = null;
            for (HomeDataImpl home : u.getHomes()) {
                if (home.getName().equals(homeName)) _home = home;
            }
            if ((_home == null) || (u.getHomes().size() == 0)) {
                Util.sendMessage(p, MessagesImpl.HOME_UNKNOWN);
                return;
            }
            TeleportUtils tpu = new TeleportUtils(p);
            tpu.teleport(_home.getLocation());
            Util.sendMessage(p, MessagesImpl.HOME_SUCCESS
                    .replace("%home%", _home.getName()));
            return;
        }
    }

}
