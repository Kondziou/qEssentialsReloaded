package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class WorldCommand extends CommandImpl {

    public WorldCommand() {
        super("world", "Teleports player to another world", "/world [world]", "world", Arrays.asList("qworld", "w", "swiat"), false);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if ((args.length >= 2) || (args.length == 0)) {
            for (String str : MessagesImpl.WORLD_LIST_HEADER) {
                Util.sendMessage(s, str);
            }
            for (World world : Bukkit.getWorlds()) {
                Util.sendMessage(s, MessagesImpl.WORLD_INDEX
                        .replace("%world%", world.getName()));
            }
            return;
        } else {
            if (!(s instanceof Player)) {
                Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
                return;
            }
            Player p = (Player) s;
            World world = Bukkit.getWorld(args[0]);
            if (world == null) {
                Util.sendMessage(p, MessagesImpl.WORLD_NOT_EXISTS);
                return;
            }
            List<String> blockedWorlds = Main.getInstance().getConfig().getStringList("blocked-worlds");
            if (blockedWorlds.contains(world.getName())) {
                Util.sendMessage(p, MessagesImpl.WORLD_BLOCKED);
                return;
            }
            TeleportUtils tpu = new TeleportUtils(p);
            tpu.teleport(world.getSpawnLocation());
            return;
        }
    }

}
