package me.kavzaq.qEssentialsReloaded.utils;

import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.Bukkit;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtils {

    private int index;
    private final LogType defaultType;
    private final String prefix;

    private static final Logger logger = Bukkit.getLogger();

    public LogUtils() {
        this.defaultType = LogType.INFO;
        this.prefix = "";
        this.index = 0;
    }

    public LogUtils(LogType defaultType) {
        this.defaultType = defaultType;
        this.prefix = "";
        this.index = 0;
    }

    public LogUtils(String prefix) {
        this.defaultType = LogType.INFO;
        this.prefix = prefix;
        this.index = 0;
    }

    public LogUtils(LogType defaultType, String prefix) {
        this.defaultType = defaultType;
        this.prefix = prefix;
        this.index = 0;
    }

    public enum LogType {
        INFO,
        WARN,
        ERROR
    }

    public void send(Throwable th, String m) {
        sendInformation();
        logger.log(Level.SEVERE, prefix + m, th);
        logger.log(Level.SEVERE, prefix + " ");
        Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
    }

    public void send(Throwable th) {
        sendInformation();
        logger.log(Level.SEVERE, th.getMessage(), th);
        logger.log(Level.SEVERE, prefix + " ");
        Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
    }

    public void send(LogType lt, String m) {
        logger.log(getLevel(lt), prefix + m);
    }

    public void send(String m) {
        logger.info(prefix + m);
    }

    public void debug(Class clazz) {
        index++;
        send(prefix + "Debug [" + clazz.getName() + "]:   " + index);
    }

    public void debugClear() {
        index = 0;
    }

    public static Level getLevel(LogType lt) {
        switch (lt) {
            case INFO:
                return Level.INFO;
            case WARN:
                return Level.WARNING;
            case ERROR:
                return Level.SEVERE;
        }
        return Level.INFO;
    }

    private void sendInformation() {
        logger.log(Level.SEVERE, prefix + " ");
        logger.log(Level.SEVERE, prefix + "           << Error occured with qEssentialsReloaded v" + Main.getInstance().getDescription().getVersion() + " >>");
        logger.log(Level.SEVERE, prefix + " ");
        logger.log(Level.SEVERE, prefix + "Informations: ");
        logger.log(Level.SEVERE, prefix + "  Maximum memory: " + (Runtime.getRuntime().maxMemory() / 1024L / 1024L) + " MB");
        logger.log(Level.SEVERE, prefix + "  Total memory: " + (Runtime.getRuntime().totalMemory() / 1024L / 1024L) + " MB");
        logger.log(Level.SEVERE, prefix + "  Free memory: " + (Runtime.getRuntime().freeMemory() / 1024L / 1024L) + " MB");
        logger.log(Level.SEVERE, prefix + "  Processor cores: " + Runtime.getRuntime().availableProcessors());
        logger.log(Level.SEVERE, prefix + "  Java version: " + System.getProperty("java.version"));
        logger.log(Level.SEVERE, prefix + "  OS information: " + ManagementFactory.getOperatingSystemMXBean().getName() + ", v" + ManagementFactory.getOperatingSystemMXBean().getVersion());
        logger.log(Level.SEVERE, prefix + "  Uptime: " + Util.parseTime(ManagementFactory.getRuntimeMXBean().getUptime()) + " / " + ManagementFactory.getRuntimeMXBean().getUptime());
        logger.log(Level.SEVERE, prefix + " ");
        logger.log(Level.SEVERE, prefix + "Stacktrace: ");
        logger.log(Level.SEVERE, prefix + " ");
    }

}
