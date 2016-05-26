package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.data.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.SerializeUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class DelHomeCommand extends CommandImpl {

    public DelHomeCommand() {
        super("delhome", "Deletes a home", "/delhome <name>", "delhome", Arrays.asList("qdelhome", "usundom"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player)s;
        if ((args.length == 0) || (args.length >= 2)) {
            Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        UserImpl u = Main.getUserManager().getUser(p);
        
        String homeName = args[0];
        HomeDataImpl _home = null;
        for (HomeDataImpl home : u.getHomes()) {
            if (home.getName().equals(homeName)) _home = home;
        }
        if ((_home == null) || (u.getHomes().size() == 0)) {
            Util.sendMessage(p, MessagesImpl.DELHOME_UNKNOWN);
            return;
        }
        
        u.delHome(_home);
        
        Util.sendMessage(p, MessagesImpl.DELHOME_SUCCESS
                .replace("%home%", args[0]));
        
    }

}
