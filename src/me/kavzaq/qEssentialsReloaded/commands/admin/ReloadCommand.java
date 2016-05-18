package me.kavzaq.qEssentialsReloaded.commands.admin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.GroupChatManager;
import me.kavzaq.qEssentialsReloaded.io.MessageFile;
import me.kavzaq.qEssentialsReloaded.io.Messages;
import me.kavzaq.qEssentialsReloaded.io.Tablist;
import me.kavzaq.qEssentialsReloaded.io.TablistFile;
import me.kavzaq.qEssentialsReloaded.io.caches.CacheFile;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

public class ReloadCommand extends CommandImpl {

    public ReloadCommand() {
        super("qreload", "Reloads a plugin", "/qreload", "admin.reload", Arrays.asList("qrl", "przeladuj"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        // configuration files
        MessageFile.loadFile();
        Messages.loadMessages();
        Messages.saveMessages();

        TablistFile.loadFile();
        Tablist.loadTablist();
        Tablist.saveTablist();
        
        if (!CacheFile.getFile().exists()) {
            CacheFile.loadFile();
            CacheFile.getFileConfiguration().set("SPAWN_LOCATION", "world 0 100 0 0.0 0.0");   
        }
        CacheFile.save();
        CacheFile.saveDefaultConfig();
       
        Main.getInstance().saveDefaultConfig();
        File f = new File(Main.getInstance().getDataFolder(), "config.yml");
        try {
            if (!Main.getInstance().getDataFolder().exists()) Main.getInstance().getDataFolder().mkdir();
            if (!f.exists()) f.createNewFile();
            Main.getInstance().getConfig().load(f);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
        Main.getTabExecutor().loadTab();
        Main.getKitManager().load();
        GroupChatManager.loadGroups();
        Util.sendMessage(s, MessagesImpl.RELOAD_SUCCESS.replace("%version%", Main.getInstance().getDescription().getVersion()));
    }
    
}
