package me.kavzaq.qEssentialsReloaded.io;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.lang.reflect.Field;

public class Messages {

    private Messages() {
    }

    public static void saveMessages() {
        FileConfiguration data = MessageFile.getFileConfiguration();
        for (Field fld : MessagesImpl.class.getFields()) {
            if (!data.isSet(fld.getName())) {
                try {
                    data.set(fld.getName(), fld.get(fld.getName()));
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Main.log.send(ex);
                }
            }
        }

        try {
            data.save(MessageFile.getFile());
        } catch (IOException ex) {
            Main.log.send(ex);
        }
    }

    public static void loadMessages() {
        try {
            final FileConfiguration data = MessageFile.getFileConfiguration();
            for (final Field fld : MessagesImpl.class.getFields()) {
                if (data.isSet(fld.getName())) {
                    if (Util.isFieldList(fld)) fld.set(null,
                            data.getStringList(fld.getName().replace("\\n", "\n")));
                    else {
                        fld.set(null, data.get(fld.getName()));
                    }
                }
            }
        } catch (Exception ex) {
            Main.log.send(ex);
        }

    }
}
