package me.kavzaq.qEssentialsReloaded.impl.managers;

import com.google.common.collect.Maps;
import java.util.HashMap;
import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.configuration.ConfigurationSection;

public class GroupChatManager {
    
    private GroupChatManager() { }
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
        if (!preloaded) { // nadal nie potrafie naprawic tego bledu. chuj wie co jest.
            Main.Debug("[qEssentialsReloaded] [Configuration] An error occured: groups did not loaded successfuly. Please /reload or restart server!");
        }
    }
    
}
