package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TpCommand extends CommandImpl {

    public TpCommand() {
        super("tp", "Teleporting management", "/tp <player/[all]> [player]", "teleport", Arrays.asList("qtp", "teleportuj"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if((args.length == 0) || (args.length > 2)){
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        if(args.length == 1){
            if(!(s instanceof Player)){
                Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
                return;
            }
            Player p = (Player)s;
            if(args[0].equalsIgnoreCase("all")){
                if(!p.hasPermission("qessentials.teleport.all")){
                    Util.sendMessage(p, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.teleport.all"));
                    return;
                }
                Util.sendMessage(p, MessagesImpl.TP_ALL_SUCCESS);
                for (Player online : Bukkit.getOnlinePlayers()){
                    if (online.equals(p)) return;
                    TeleportUtils tpu = new TeleportUtils(online);
                    tpu.teleport(p.getLocation());
                }
                return;
            }else{
                Player tpTo = Bukkit.getPlayerExact(args[0]);
                if (s.equals(tpTo)) {
                    Util.sendMessage(s, MessagesImpl.SAME_PERSON);
                    return;
                }
                if(!(tpTo == null)){
                    if (s.equals(tpTo)) {
                        Util.sendMessage(s, MessagesImpl.TELEPORT_SAME_PERSON);
                        return;
                    }
                    TeleportUtils tpu = new TeleportUtils(p);
                    tpu.teleport(tpTo.getLocation());
                    return;
                }else{
                    Util.sendMessage(p, MessagesImpl.OFFLINE_PLAYER);
                    return;
                }
            }
        }
        if(args.length == 2){
            if(s.hasPermission("qessentials.teleport.others")){
                Player tpFrom = Bukkit.getPlayer(args[0]);
                Player tpTo = Bukkit.getPlayer(args[1]);
                if ((!(tpFrom == null)) || (!(tpTo == null))){
                    if (tpFrom.equals(tpTo)) {
                        Util.sendMessage(s, MessagesImpl.SAME_PERSONS);
                        return;
                    }
                    if ((s.equals(tpTo)) || (s.equals(tpFrom))) {
                        Util.sendMessage(s, MessagesImpl.SAME_PERSON);
                        return;
                    }
                    Util.sendMessage(s, MessagesImpl.TP_FROM_TO_SUCCESS
                            .replace("%player_from%", tpFrom.getName())
                            .replace("%player_to%", tpTo.getName()));
                    TeleportUtils tpu = new TeleportUtils(tpFrom);
                    tpu.teleport(tpTo.getLocation());
                    Util.sendMessage(tpFrom, MessagesImpl.TP_TELEPORTED
                            .replace("%player%", tpTo.getName()));
                } else {
                    Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYERS);
                    return;
                }
            }else{
                Util.sendMessage(s, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.teleport.others"));
                return;
            }
        }
        
    }

}
