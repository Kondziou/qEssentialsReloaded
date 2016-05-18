package me.kavzaq.qEssentialsReloaded.commands;

import java.lang.reflect.Field;
import me.kavzaq.qEssentialsReloaded.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

public class CommandManager {
    
    private CommandManager() { }
    
    public static CommandMap getCommandMap() {
        Field field = null;
        try {
            field = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (NoSuchFieldException | SecurityException e) {
            Main.log.send(e);
        }
        field.setAccessible(true);
        try {
            return (CommandMap)field.get(Bukkit.getServer().getPluginManager());
        } catch (IllegalArgumentException | IllegalAccessException e) {
            Main.log.send(e);
        }
        return null;
    }

    public static boolean registerCommand(Command command) {
        getCommandMap().register("qessentials", command);
        return false;
    }

}
