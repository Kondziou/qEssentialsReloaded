package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class InvseeCommand extends CommandImpl {

    public InvseeCommand() {
        super("invsee", "Shows a someones inventory", "/invsee <player>", "invsee", Arrays.asList("qinvsee", "inv"), true);
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
        if (other.hasPermission("qessentials.invsee.bypass")) {
            Util.sendMessage(p, MessagesImpl.BYPASS_PERMISSION);
            return;
        }
        p.openInventory(other.getInventory());
    }

}
