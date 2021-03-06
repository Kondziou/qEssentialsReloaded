package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.io.caches.CacheManager;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SpawnCommand extends CommandImpl {

    public SpawnCommand() {
        super("spawn", "Teleports player to a spawn", "/spawn [player]", "spawn", Arrays.asList("qspawn"));
    }

    private static Location spawnLocation = null;

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length >= 2) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    spawnLocation = CacheManager.getSpawnLocation();

                    if (args.length == 0) {
                        if (!(s instanceof Player)) {
                            Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
                            return;
                        }
                        Player p = (Player) s;

                        new TeleportUtils(p).teleport(spawnLocation);
                        if (Main.getInstance().getConfig().getBoolean("teleport-spawn-effects")) {
                            p.getWorld().playEffect(spawnLocation, Effect.MOBSPAWNER_FLAMES, 0, 0);
                            p.getWorld().playEffect(spawnLocation, Effect.SMOKE, 0, 0);
                        }
                        return;
                    }
                    if (args.length == 1) {
                        if (Bukkit.getPlayer(args[0]) == null) {
                            Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
                            return;
                        }
                        Player other = Bukkit.getPlayer(args[0]);
                        if (s.equals(other)) {
                            Util.sendMessage(s, MessagesImpl.SAME_PERSON);
                            return;
                        }
                        new TeleportUtils(other).teleport(spawnLocation);
                        if (Main.getInstance().getConfig().getBoolean("teleport-spawn-effects")) {
                            other.getWorld().playEffect(spawnLocation, Effect.MOBSPAWNER_FLAMES, 0, 0);
                            other.getWorld().playEffect(spawnLocation, Effect.SMOKE, 0, 0);
                        }
                        Util.sendMessage(s, MessagesImpl.SPAWN_OTHER_SUCCESS
                                .replace("%player%", other.getName()));
                        Util.sendMessage(other, MessagesImpl.SPAWN_OTHER_TELEPORTED
                                .replace("%player%", s.getName()));
                    }
                } catch (Exception e) {
                    Main.log.send(e);
                    return;
                }
            }
        });

    }
}
