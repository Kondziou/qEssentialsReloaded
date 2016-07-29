package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class KillCommand extends CommandImpl {

    public KillCommand() {
        super("kill", "Kills a player", "/kill [player]", "kill", Arrays.asList("qkill", "zabij", "slay"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length >= 2) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        if (args.length == 0) {
            Player p = (Player) s;
            if (!(p instanceof Player)) {
                Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
                return;
            }
            p.setHealth(0.0);
            Util.sendMessage(p, MessagesImpl.KILL_SUCCESS);
            return;
        } else if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
                return;
            }
            Player other = Bukkit.getPlayer(args[0]);
            if (s.equals(other)) {
                Util.sendMessage(s, MessagesImpl.SAME_PERSON);
                return;
            }
            other.setHealth(0.0);
            Util.sendMessage(s, MessagesImpl.KILL_OTHER_SUCCESS
                    .replace("%player%", other.getName()));
            Util.sendMessage(other, MessagesImpl.KILL_OTHER_KILLED
                    .replace("%player%", s.getName()));
        }
    }

}
