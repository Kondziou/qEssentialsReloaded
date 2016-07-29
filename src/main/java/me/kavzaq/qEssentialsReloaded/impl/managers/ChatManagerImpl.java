package me.kavzaq.qEssentialsReloaded.impl.managers;

import com.google.common.collect.Maps;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.LogUtils.LogType;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class ChatManagerImpl {

    private ChatManagerImpl() {
    }

    private static final HashMap<String, String> groups = Maps.newHashMap();

    public static HashMap<String, String> getGroups() {
        return groups;
    }

    public static void loadGroups() {
        boolean preloaded = false;
        ConfigurationSection csGroups = Main.getInstance().getConfig().getConfigurationSection("groups");
        for (String groupName : csGroups.getKeys(true)) {
            preloaded = true;
            String groupContent = csGroups.getString(groupName);
            groups.put(groupName, groupContent);
        }
        if (!preloaded) {
            Main.log.send(LogType.WARN, "[qEssentialsReloaded] [Configuration] An error occured: groups did not loaded successfuly. Please /reload or restart server!");
        }
    }

}
