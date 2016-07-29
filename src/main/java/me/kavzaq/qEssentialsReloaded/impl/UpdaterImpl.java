package me.kavzaq.qEssentialsReloaded.impl;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;

public class UpdaterImpl {

    private static boolean actualVersion = true;
    private static String newestVersion = "1.0.0R";
    private static final String currentVersion =
            Main.getInstance().getDescription().getVersion();

    public static boolean isUpdated() {
        return actualVersion;
    }

    public static String getNewestVersion() {
        return newestVersion;
    }

    public static String getCurrentVersion() {
        return currentVersion;
    }

    public static void checkUpdate() {
        if (!Main.getInstance().getConfig().getBoolean("updater")) {
            newestVersion = currentVersion;
            actualVersion = true;
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            try {
                newestVersion = Util.readUrl("http://kavzaq.cba.pl/plugins/qessentials/update.txt");
            } catch (Exception e) {
                Main.log.send(e);
                return;
            }
            if (!currentVersion.equalsIgnoreCase(newestVersion)) {
                actualVersion = false;
            }
        });
    }

    private UpdaterImpl() {
    }

}
