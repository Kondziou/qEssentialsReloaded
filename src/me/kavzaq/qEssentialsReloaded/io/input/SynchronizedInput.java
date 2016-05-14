package me.kavzaq.qEssentialsReloaded.io.input;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import me.kavzaq.qEssentialsReloaded.Main;

public class SynchronizedInput {
    
    private static final HashMap<String, SoftReference<SynchronizedInput>> cache = Maps.newHashMap();
    private final transient long lastChanged;
    private transient List<String> lines;
    
    public List<String> getLines() {
        return lines;
    }
    
    public SynchronizedInput(String fileName) throws IOException, FileNotFoundException {
        File folder = Main.getInstance().getDataFolder();
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        boolean read = false;
        lastChanged = file.lastModified();
        synchronized (cache) {
            SoftReference<SynchronizedInput> input = cache.get(file.getName());
            SynchronizedInput in = null;
            if (input == null 
                    || (in = input.get()) == null 
                    || in.lastChanged < lastChanged) {
                lines = Lists.newArrayList();
                cache.put(file.getName(), new SoftReference<>(this));
                
                read = true;
            }
            else {
                lines = Collections.unmodifiableList(in.lines);
            }
        }
        if (read) {
            try {
                Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader buffer = new BufferedReader(reader);
                
                try {
                    while (buffer.ready()) {
                        lines.add(buffer.readLine());
                    }
                }
                finally {
                    reader.close();
                    buffer.close();
                }   
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
