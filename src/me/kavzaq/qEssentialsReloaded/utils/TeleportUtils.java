package me.kavzaq.qEssentialsReloaded.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;

public class TeleportUtils {
    
    public static List<Player> users = Lists.newArrayList();
    
    private Player player;
    private BukkitTask task;
    
    public TeleportUtils(Player player) {
        this.player = player;
    }
    
    public void teleport(Location loc) {
        if (player.hasPermission("qessentials.teleport.bypass")) {
            player.teleport(loc);
            return;
        }
        
        users.add(player);
        Util.sendMessage(player, MessagesImpl.TELEPORT_PROCESS
                .replace("%delay%", String.valueOf(Main.getInstance().getConfig().getLong("delay"))));
        
        task = Main.getInstance().getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (users.contains(player)) {
                    if (!player.isOnline()) {
                        users.remove(player);
                        return;
                    }
                    
                    player.teleport(loc);
                    Util.sendMessage(player, MessagesImpl.TELEPORT_SUCCESS);
                    users.remove(player);
                    Bukkit.getScheduler().cancelTask(task.getTaskId());
                } 
            }
        }, Main.getInstance().getConfig().getLong("delay") * 20L);
    }
    
    public void teleport(Location loc, boolean dontSend) {
        if (player.hasPermission("qessentials.teleport.bypass")) {
            player.teleport(loc);
            return;
        }
        
        users.add(player);
        Util.sendMessage(player, MessagesImpl.TELEPORT_PROCESS
                .replace("%delay%", String.valueOf(Main.getInstance().getConfig().getLong("delay"))));
        
        task = Main.getInstance().getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (users.contains(player)) {
                    if (!player.isOnline()) {
                        users.remove(player);
                        return;
                    }
                    
                    player.teleport(loc);
                    if (!dontSend) Util.sendMessage(player, MessagesImpl.TELEPORT_SUCCESS);
                    users.remove(player);
                    Bukkit.getScheduler().cancelTask(task.getTaskId());
                } 
            }
        }, Main.getInstance().getConfig().getLong("delay") * 20L);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
