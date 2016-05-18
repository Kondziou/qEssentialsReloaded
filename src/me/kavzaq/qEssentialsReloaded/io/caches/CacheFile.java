package me.kavzaq.qEssentialsReloaded.io.caches;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CacheFile {
    
    private CacheFile() { }
    
    private static final String dataFolderPath = Main.getInstance().getDataFolder().getAbsolutePath() + "/cache/";
    private static final File dataFolder = Paths.get(dataFolderPath).toFile();
    private static final File cacheFile = new File(dataFolder, "spawn.yml");
    private static FileConfiguration fileConf;
    
    public static File getFile()
    {
        return cacheFile;
    }

    public static void loadFile()
    {
        if(!dataFolder.exists())
        {
            dataFolder.mkdirs();
        }
        else if(!cacheFile.exists())
        {
            try{
                cacheFile.createNewFile();
            } catch(IOException ex) {
                Main.log.send(ex);
            }
        } 
        
    }
    
    public static void saveDefaultConfig() {
        if (!cacheFile.exists()) {            
            Main.getInstance().saveResource(cacheFile.getName(), false);
        }
    }
    
    public static void save() {
        try {
            getFileConfiguration().save(cacheFile);
        } catch (IOException e) { Main.log.send(e); }
    }
    
    public static void reload() {
        fileConf = YamlConfiguration.loadConfiguration(cacheFile);
        InputStream is = Main.getInstance().getResource(cacheFile.getAbsolutePath());
        if (is != null) {
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(new InputStreamReader(is));
            fileConf.setDefaults(conf);
        }
    }

    public static FileConfiguration getFileConfiguration()
    {
        if (fileConf == null) {
            reload();
        }
        return fileConf;
    }
    
}
