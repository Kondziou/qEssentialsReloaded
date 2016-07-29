package me.kavzaq.qEssentialsReloaded.utils;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.configuration.TabConfigurationImpl;
import me.kavzaq.qEssentialsReloaded.io.TablistFile;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map.Entry;

public class TablistUtils {

    private static List<String> strings;

    private TablistUtils() {
    }

    public static void configureMessages() {
        strings = Lists.newArrayList();
        for (Entry<String, Object> entry : TablistFile.getFileConfiguration().getValues(true).entrySet()) {
            if (entry.getKey().startsWith("tabSlot_")) {
                strings.add(entry.getValue().toString());
            }
        }
    }

    public static void updateTab(Player p) {
        int message = 0;
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().updateSlot(p, i, 0, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().updateSlot(p, i, 1, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().updateSlot(p, i, 2, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().updateSlot(p, i, 3, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        Main.getTabExecutor().executeTab(p);
    }

    public static void showTab(Player p) {
        Main.getTabExecutor().clearTab(p);
        Main.getTabManager().sendPacketHeaderFooter(p,
                TabConfigurationImpl.tablistHeader, TabConfigurationImpl.tablistFooter);

        int message = 0;
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().addSlot(p, i, 0, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().addSlot(p, i, 1, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().addSlot(p, i, 2, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        for (int i = 0; i <= 19; i++) {
            Main.getTabExecutor().addSlot(p, i, 3, Util.fixColors(ReplaceUtils.replaceVariables(p, strings.get(message))));
            message++;
        }
        Main.getTabExecutor().executeTab(p);
    }


}
