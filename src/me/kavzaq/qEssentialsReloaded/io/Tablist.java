package me.kavzaq.qEssentialsReloaded.io;

import java.io.IOException;
import java.lang.reflect.Field;
import me.kavzaq.qEssentialsReloaded.Main;

import org.bukkit.configuration.file.FileConfiguration;

import me.kavzaq.qEssentialsReloaded.impl.TabConfigurationImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class Tablist {
    
    private Tablist() { }
    
    public static void saveTablist() {
        FileConfiguration data = TablistFile.getFileConfiguration();
        for(Field fld : TabConfigurationImpl.class.getFields()) {
            if(!data.isSet(fld.getName())) {
                try {
                    data.set(fld.getName(), fld.get(fld.getName()));
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Main.log.send(ex);
                }
            }
        }
                
        try {
            data.save(TablistFile.getFile());
        } catch (IOException ex) {
            Main.log.send(ex);
        }
    }
    
    public static void loadTablist()
    {
        try{
            final FileConfiguration data = TablistFile.getFileConfiguration();
            for(final Field fld : TabConfigurationImpl.class.getFields())
            {
                if(data.isSet(fld.getName())) {
                    if(Util.isFieldList(fld)) fld.set(null, 
                        data.getStringList(fld.getName().replace("\\n", "\n")));
                    else {
                        fld.set(null, data.get(fld.getName()));
                    }
                }
            }
        }catch(Exception ex) {
            Main.log.send(ex);
        }
    
    }

}
