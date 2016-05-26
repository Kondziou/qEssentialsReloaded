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
import org.apache.commons.lang.StringUtils;

public class SetHomeCommand extends CommandImpl {

    public SetHomeCommand() {
        super("sethome", "Sets a new home", "/sethome <name>", "sethome", Arrays.asList("qsethome", "ustawdom"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player)s;
        if ((args.length == 0) || (args.length >= 2)) {
            Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        UserImpl u = Main.getUserManager().getUser(p);
        
        int maxhomes = Main.getUserManager().availableHomes(p);

        if ((u.getHomes().size() >= maxhomes) && (maxhomes != -1)) {
            Util.sendMessage(p, MessagesImpl.SETHOME_MAX);
            return;
        }
        
        for (HomeDataImpl home : u.getHomes()) {
            if (home.getName().equals(args[0])){
                Util.sendMessage(p, MessagesImpl.SETHOME_HAS_THIS_NAME);
                return;
            }
        }
        
        if (!StringUtils.isAlpha(args[0])) {
            Util.sendMessage(p, MessagesImpl.SETHOME_INVALID);
            return;
        }
        
        u.addHome(new HomeDataImpl(args[0], p.getLocation()));
        
        Util.sendMessage(p, MessagesImpl.SETHOME_SUCCESS
                .replace("%home%", args[0]));
    }

}
