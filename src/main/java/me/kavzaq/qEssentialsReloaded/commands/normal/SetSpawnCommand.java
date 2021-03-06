package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.io.caches.CacheManager;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SetSpawnCommand extends CommandImpl {

    public SetSpawnCommand() {
        super("setspawn", "Sets a spawn", "/setspawn", "setspawn", Arrays.asList("qsetspawn", "ustawspawn"), true);
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player) s;
        Location loc = p.getLocation();
        World w = p.getWorld();
        w.setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());

        Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    CacheManager.setSpawnLocation(loc);
                } catch (Exception e) {
                    Main.log.send(e);
                    return;
                }

                String coords = "x" + loc.getBlockX() + ", y" + loc.getBlockY() + ", z" + loc.getBlockZ();

                Util.sendMessage(p, MessagesImpl.SETSPAWN_SUCCESS
                        .replace("%coords%", coords)
                        .replace("%world%", w.getName()));
            }
        });

    }

}
