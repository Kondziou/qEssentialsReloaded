package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpPosCommand extends CommandImpl {

    public TpPosCommand() {
        super("tppos", "Teleports to a coordinate", "/tppos <x> <y> <z> [player]", "tppos", Arrays.asList("qtppos", "tpp"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if ((args.length >= 5) || (args.length <= 2)) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        if (args.length == 3) {
            if (!(s instanceof Player)) {
                Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
                return;
            }
            Player p = (Player)s;
            int x,y,z;
            try {
                x = Integer.valueOf(args[0]);
                y = Integer.valueOf(args[1]);
                z = Integer.valueOf(args[2]);
            } catch (Exception e) {
                Util.sendMessage(p, MessagesImpl.TPPOS_COORDS_INVALID);
                return;
            }
            TeleportUtils tpu = new TeleportUtils(p);
            tpu.teleport(new Location(p.getWorld(), x, y, z), true);
            Util.sendMessage(p, MessagesImpl.TPPOS_SUCCESS
                .replace("%coords%", "x" + x + ", y" + y + ", z" + z));
        }
        else if (args.length == 4) {
            if (Bukkit.getPlayer(args[3]) == null) {
                Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
                return;
            }
            Player other = Bukkit.getPlayer(args[3]);
            int x,y,z;
            try {
                x = Integer.valueOf(args[0]);
                y = Integer.valueOf(args[1]);
                z = Integer.valueOf(args[2]);
            } catch (Exception e) {
                Util.sendMessage(s, MessagesImpl.TPPOS_COORDS_INVALID);
                return;
            }
            TeleportUtils tpu = new TeleportUtils(other);
            tpu.teleport(new Location(other.getWorld(), x, y, z), true);
            Util.sendMessage(s, MessagesImpl.TPPOS_OTHER_SUCCESS
                .replace("%coords%", "x" + x + ", y" + y + ", z" + z)
                .replace("%player%", other.getName()));
            Util.sendMessage(other, MessagesImpl.TPPOS_OTHER_TELEPORTED
                .replace("%coords%", "x" + x + ", y" + y + ", z" + z)
                .replace("%player%", s.getName()));
        }
    }
    
}
