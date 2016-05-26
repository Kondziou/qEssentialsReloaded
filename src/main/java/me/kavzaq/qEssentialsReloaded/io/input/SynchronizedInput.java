package me.kavzaq.qEssentialsReloaded.io.input;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    private transient File file;
    
    public List<String> getLines() {
        return lines;
    }
    
    public File getFile() {
        return file;
    }
    
    public final void write(List<String> lines) {
        try {
            InputStream is = new FileInputStream(file.getName());
            OutputStream os = new FileOutputStream(file);
            try
            {
                byte[] buff = new byte[1024];
                int len = is.read(buff);
                while (len > 0)
                {
                    os.write(buff, 0, len);
                    len = is.read(buff);
                }
                PrintWriter pw = new PrintWriter(file.getName());
                for (String s : lines) {
                    pw.write(s);
                }
                pw.close();
            }
            finally
            {
                os.close();
                is.close();
            }
        } catch (IOException ex) {
            Main.log.send(ex);
        }
       
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
                this.file = file;
            } catch (IOException ex) {
                Main.log.send(ex);
            }
        }
        boolean read = false;
        this.lastChanged = file.lastModified();
        this.file = file;
        if (file.exists()) {
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
                    Main.log.send(ex);
                }
            }
        } else {
            
        }
    }
    
}
