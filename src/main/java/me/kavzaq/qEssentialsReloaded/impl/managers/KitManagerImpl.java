package me.kavzaq.qEssentialsReloaded.impl.managers;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.KitImpl;
import me.kavzaq.qEssentialsReloaded.utils.LogUtils.LogType;

public class KitManagerImpl {
    
    private static final StringBuilder localsb = new StringBuilder();

    public void load() {
        boolean preloaded = false;
        ConfigurationSection csKits = Main.getInstance().getConfig().getConfigurationSection("kits");
        for (String kitStr : csKits.getKeys(false)) {
            preloaded = true;
            ConfigurationSection cs = csKits.getConfigurationSection(kitStr);
            
            int cooldown = cs.getInt("cooldown");
            List<String> items = cs.getStringList("items");
            
            KitImpl kit = new KitImpl(kitStr);
            kit.setCooldown(cooldown * 1000);
            kit.setItems(items);
            KitImpl.list.add(kit);
        }
        if (!preloaded) { // nadal nie potrafie naprawic tego bledu. chuj wie co jest.
            Main.log.send(LogType.WARN, "[qEssentialsReloaded] [Configuration] An error occured: kits did not loaded successfuly. Please /reload or restart server!");
        }
    }

    
    public String getKits() {
        localsb.setLength(0);
        for (String kitStr : Main.getInstance().getConfig().getConfigurationSection("kits").getKeys(false)){
            localsb.append(", " + kitStr);
        }
        if (localsb.length() == 0) {
            localsb.append("*none*");
        }
        return localsb.toString().replaceFirst(", ", "");
    }

}
