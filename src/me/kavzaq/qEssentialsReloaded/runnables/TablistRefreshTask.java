package me.kavzaq.qEssentialsReloaded.runnables;

import me.kavzaq.qEssentialsReloaded.impl.TabConfigurationImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.utils.TablistUtils;

public class TablistRefreshTask implements Runnable {

    @Override
    public void run() {
        if (TabConfigurationImpl.tablistEnabled) {
            try {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    TablistUtils.updateTab(p);
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

}
