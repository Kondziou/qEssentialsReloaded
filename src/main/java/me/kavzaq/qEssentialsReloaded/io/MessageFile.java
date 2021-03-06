package me.kavzaq.qEssentialsReloaded.io;

import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageFile {

    private MessageFile() {
    }

    private static final File dataFolder = Main.getInstance().getDataFolder();
    private static final File messageFile = new File(dataFolder, "messages_"
            + Main.getInstance().getConfig().getString("language") + ".yml");

    public static File getFile() {
        return messageFile;
    }

    public static void loadFile() {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        } else if (!messageFile.exists()) {
            try {
                messageFile.createNewFile();
                return;
            } catch (IOException ex) {
                Main.log.send(ex);
            }
        }
    }

    public static FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(messageFile);
    }

}
