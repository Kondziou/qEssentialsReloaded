package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;

public class ListCommand extends CommandImpl {

    public ListCommand() {
        super("list", "Online players", "/list", "list", Arrays.asList("qlist", "lista", "online"));

    }

    private static final StringBuilder localsb = new StringBuilder();

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Collection<? extends Player> online = Bukkit.getOnlinePlayers();
        int players = online.size();
        Util.sendMessage(s, MessagesImpl.LIST_HEADER
                .replace("%players%", String.valueOf(players))
                .replace("%maxplayers%", String.valueOf(Bukkit.getMaxPlayers())));
        localsb.setLength(0);
        for (Player p : online) {
            localsb.append(", " + p.getName());
        }
        Util.sendMessage(s, MessagesImpl.LIST_INDEX.replace("%players%", localsb.toString().replaceFirst(", ", "")));

    }

}
