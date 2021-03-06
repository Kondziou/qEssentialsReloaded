package me.kavzaq.qEssentialsReloaded.io;

import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class TablistFile {

    private TablistFile() {
    }

    private static final File dataFolder = Main.getInstance().getDataFolder();
    private static final File tablistFile = new File(dataFolder, "tablist.yml");

    public static File getFile() {
        return tablistFile;
    }

    public static void loadFile() {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        } else if (!tablistFile.exists()) {
            try {
                tablistFile.createNewFile();
                return;
            } catch (IOException ex) {
                Main.log.send(ex);
            }
        }

    }

    public static FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(tablistFile);
    }

}
