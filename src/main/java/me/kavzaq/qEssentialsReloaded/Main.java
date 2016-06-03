package me.kavzaq.qEssentialsReloaded;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import me.kavzaq.qEssentialsReloaded.commands.CommandManager;
import me.kavzaq.qEssentialsReloaded.commands.admin.ReloadCommand;
import me.kavzaq.qEssentialsReloaded.commands.aliases.DayAlias;
import me.kavzaq.qEssentialsReloaded.commands.aliases.NightAlias;
import me.kavzaq.qEssentialsReloaded.commands.aliases.SunAlias;
import me.kavzaq.qEssentialsReloaded.commands.aliases.ThunderAlias;
import me.kavzaq.qEssentialsReloaded.commands.normal.BackCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.BanCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.BroadcastCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ChatCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ClearInventoryCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.DelHomeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.DelWarpCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.DisplayNameCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.EnchantCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.EnderchestCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.FeedCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.FlyCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.GameModeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.GarbageCollectorCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.GiveCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.GodCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HatCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HeadCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HealCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HelpCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HelpopCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HomeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.InvseeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ItemCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.KickCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.KillCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.KitCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ListCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.MessageCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.PowerToolCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.RepairCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ReplyCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.SetHomeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.SetSpawnCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.SetWarpCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.SpawnCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TempBanCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TimeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpAcceptCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpDenyCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpHereCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpPosCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpaCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.UnbanCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.WarpCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.WeatherCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.WhoIsCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.WorldCommand;
import me.kavzaq.qEssentialsReloaded.database.Database;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.TabConfigurationImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.KitManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.UserManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.containers.MessageContainerImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.BanManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.packet.TabExecutorImpl;
import me.kavzaq.qEssentialsReloaded.impl.packet.TabManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.teleport.TeleportRequestImpl;
import me.kavzaq.qEssentialsReloaded.impl.teleport.TeleportUpdaterImpl;
import me.kavzaq.qEssentialsReloaded.io.MessageFile;
import me.kavzaq.qEssentialsReloaded.io.Messages;
import me.kavzaq.qEssentialsReloaded.io.Tablist;
import me.kavzaq.qEssentialsReloaded.io.TablistFile;
import me.kavzaq.qEssentialsReloaded.listeners.AsyncPlayerChatListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerLoginListener;
import me.kavzaq.qEssentialsReloaded.listeners.EntityDamageListener;
import me.kavzaq.qEssentialsReloaded.listeners.FoodLevelChangeListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerJoinListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerMoveListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerQuitListener;
import me.kavzaq.qEssentialsReloaded.listeners.SignChangeListener;
import me.kavzaq.qEssentialsReloaded.runnables.AutoMessageTask;
import me.kavzaq.qEssentialsReloaded.runnables.TablistRefreshTask;
import me.kavzaq.qEssentialsReloaded.runnables.metrics.MetricsCollector;
import me.kavzaq.qEssentialsReloaded.runnables.tpsmonitor.TPSMonitor;
import me.kavzaq.qEssentialsReloaded.utils.EnchantmentUtils;
import me.kavzaq.qEssentialsReloaded.utils.PaginatorUtils;
import me.kavzaq.qEssentialsReloaded.utils.TablistUtils;
import me.kavzaq.qEssentialsReloaded.impl.managers.ChatManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.WarpManagerImpl;
import me.kavzaq.qEssentialsReloaded.io.caches.CacheFile;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerDeathListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerInteractListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerRespawnListener;
import me.kavzaq.qEssentialsReloaded.utils.LogUtils;
import me.kavzaq.qEssentialsReloaded.utils.LogUtils.LogType;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class Main extends JavaPlugin {

    private static Metrics metrics;
    private static Main inst;
    private static UserManagerImpl userManager;
    private static CommandImpl command;
    private static TabManagerImpl tabmanager;
    private static TabExecutorImpl tabexecutor;
    private static TeleportRequestImpl teleportrequests;
    private static TeleportUpdaterImpl teleportupdater;
    private static MessageContainerImpl messagecontainer;
    private static KitManagerImpl kitmanager;
    private static Random random;
    private static Database database;
    
    public static final LogUtils log = new LogUtils(LogType.INFO);

    // FunnyGuilds
    public static boolean funnyguilds_support = false;

    // Vault
    public static Economy economy = null;
    public static Chat chat = null;
    public static boolean economy_support = false;
    public static boolean chat_support = false;

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider
                = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider
                = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    private long startTime;
    private long loadTime;

    public static Random getRandom() {
        return random;
    }
    
    public static Database getDb() {
        return database;
    }

    public static Metrics getMetrics() {
        return metrics;
    }

    public static Main getInstance() {
        return inst;
    }

    public static KitManagerImpl getKitManager() {
        return kitmanager;
    }

    public static MessageContainerImpl getMessageData() {
        return messagecontainer;
    }

    public static TeleportRequestImpl getTeleportRequests() {
        return teleportrequests;
    }

    public static TabManagerImpl getTabManager() {
        return tabmanager;
    }

    public static TabExecutorImpl getTabExecutor() {
        return tabexecutor;
    }

    public static CommandImpl getCommandManager() {
        return command;
    }

    public static UserManagerImpl getUserManager() {
        return userManager;
    }

    public static TeleportUpdaterImpl getTeleportUpdater() {
        return teleportupdater;
    }

    public static void Debug(String string) {
        log.send(string);
    }

    @Override
    public void onLoad() {
        log.send("[qEssentialsReloaded] [Preload] Instantiating java plugin...");
        inst = this;
    }


    @Override
    public void onEnable() {
        startTime = System.currentTimeMillis();
        log.send("[qEssentialsReloaded] Loading resources...");
        saveDefaultConfig();
        File f = new File(getDataFolder(), "config.yml");
        try {
            if (!getDataFolder().exists()) getDataFolder().mkdir();
            if (!f.exists()) f.createNewFile();
            Main.getInstance().getConfig().load(f);
        } catch (IOException | InvalidConfigurationException ex) {
            Main.log.send(ex);
        }
        log.send("[qEssentialsReloaded] Creating variables, connecting to database and creating tables...");
        if(getConfig().getBoolean("database.mysql", false)){
            database = new MySQL(getConfig().getString("database.host"),
                                 getConfig().getInt("database.port"),
                                 getConfig().getString("database.user"),
                                 getConfig().getString("database.pass"),
                                 getConfig().getString("database.name"));
        }else{
            database = new SQLite();
        }
        log.send("[qEssentialsReloaded] Instantiating object implementations...");
        userManager = new UserManagerImpl();
        tabmanager = new TabManagerImpl();
        tabexecutor = new TabExecutorImpl();
        teleportupdater = new TeleportUpdaterImpl();
        teleportrequests = new TeleportRequestImpl();
        messagecontainer = new MessageContainerImpl();
        kitmanager = new KitManagerImpl();
        log.send("[qEssentialsReloaded] Doing some miscellaneous work...");
        log.send("[qEssentialsReloaded] [Misc] Loading FunnyGuilds optionally...");
        if (!Bukkit.getPluginManager().isPluginEnabled("FunnyGuilds")) {
            log.send(LogType.WARN, "[qEssentialsReloaded] [Misc] FunnyGuilds missing, disabling tab variables...");
        } else {
            log.send("[qEssentialsReloaded] [Misc] FunnyGuilds found, enabling tab variables...");
            funnyguilds_support = true;
        }
        log.send("[qEssentialsReloaded] [Misc] Loading Vault optionally...");
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            log.send(LogType.WARN, "[qEssentialsReloaded] [Misc] Vault missing, disabling vault functions...");
        } else {
            log.send("[qEssentialsReloaded] [Misc] Vault found, enabling vault functions...");
            setupChat();
            setupEconomy();
            economy_support = true;
            chat_support = true;
            if (!Bukkit.getPluginManager().isPluginEnabled("PermissionsEx")) {
                log.send(LogType.WARN, "[qEssentialsReloaded] [Misc] PermissionsEx missing, disabling vault chat functions...");
                chat_support = false;
            }
        }
        log.send("[qEssentialsReloaded] Generating config and message files...");
        MessageFile.loadFile();
        Messages.loadMessages();
        Messages.saveMessages();

        TablistFile.loadFile();
        Tablist.loadTablist();
        Tablist.saveTablist();
        
        if (!CacheFile.getFile().exists()) {
            CacheFile.loadFile();
            CacheFile.getFileConfiguration().set("SPAWN_LOCATION", "world 0 100 0 0.0 0.0");
        }
        CacheFile.save();
        CacheFile.saveDefaultConfig();
       
        log.send("[qEssentialsReloaded] [Translation] Loaded " + MessagesImpl.LANGUAGE + " translation created by " + MessagesImpl.TRANSLATION_AUTHOR);
        log.send("[qEssentialsReloaded] Registering listeners...");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new AsyncPlayerChatListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new FoodLevelChangeListener(), this);
        pm.registerEvents(new SignChangeListener(), this);
        pm.registerEvents(new PlayerLoginListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new PlayerRespawnListener(), this);
        if (Main.getInstance().getConfig().getBoolean("kill-messages-disabled")) {
            pm.registerEvents(new PlayerDeathListener(), this);
        }
        log.send("[qEssentialsReloaded] Registering commands...");
        CommandManager.registerCommand(new GameModeCommand());
        CommandManager.registerCommand(new BroadcastCommand());
        CommandManager.registerCommand(new HealCommand());
        CommandManager.registerCommand(new ItemCommand());
        CommandManager.registerCommand(new ClearInventoryCommand());
        CommandManager.registerCommand(new TpaCommand());
        CommandManager.registerCommand(new TpAcceptCommand());
        CommandManager.registerCommand(new TpDenyCommand());
        CommandManager.registerCommand(new FeedCommand());
        CommandManager.registerCommand(new SetSpawnCommand());
        CommandManager.registerCommand(new FlyCommand());
        CommandManager.registerCommand(new BackCommand());
        CommandManager.registerCommand(new SpawnCommand());
        CommandManager.registerCommand(new TpCommand());
        CommandManager.registerCommand(new ChatCommand());
        CommandManager.registerCommand(new WeatherCommand());
        CommandManager.registerCommand(new TimeCommand());
        CommandManager.registerCommand(new ListCommand());
        CommandManager.registerCommand(new WorldCommand());
        CommandManager.registerCommand(new HomeCommand());
        CommandManager.registerCommand(new DelHomeCommand());
        CommandManager.registerCommand(new SetHomeCommand());
        CommandManager.registerCommand(new WhoIsCommand());
        CommandManager.registerCommand(new MessageCommand());
        CommandManager.registerCommand(new ReplyCommand());
        CommandManager.registerCommand(new GodCommand());
        CommandManager.registerCommand(new HelpCommand());
        CommandManager.registerCommand(new KitCommand());
        CommandManager.registerCommand(new GiveCommand());
        CommandManager.registerCommand(new EnchantCommand());
        CommandManager.registerCommand(new InvseeCommand());
        CommandManager.registerCommand(new HelpopCommand());
        CommandManager.registerCommand(new HeadCommand());
        CommandManager.registerCommand(new EnderchestCommand());
        CommandManager.registerCommand(new GarbageCollectorCommand());
        CommandManager.registerCommand(new RepairCommand());
        CommandManager.registerCommand(new KickCommand());
        CommandManager.registerCommand(new HatCommand());
        CommandManager.registerCommand(new DisplayNameCommand());
        CommandManager.registerCommand(new PowerToolCommand());
        CommandManager.registerCommand(new TpHereCommand());
        CommandManager.registerCommand(new TpPosCommand());
        CommandManager.registerCommand(new KillCommand());
        CommandManager.registerCommand(new WarpCommand());
        CommandManager.registerCommand(new SetWarpCommand());
        CommandManager.registerCommand(new DelWarpCommand());
        CommandManager.registerCommand(new BanCommand());
        CommandManager.registerCommand(new TempBanCommand());
        CommandManager.registerCommand(new UnbanCommand());
        //aliases
        CommandManager.registerCommand(new SunAlias());
        CommandManager.registerCommand(new ThunderAlias());
        CommandManager.registerCommand(new DayAlias());
        CommandManager.registerCommand(new NightAlias());
        // admin
        CommandManager.registerCommand(new ReloadCommand());
        log.send("[qEssentialsReloaded] Loading online users...");
        for (Player p : Bukkit.getOnlinePlayers()) {
            Main.getUserManager().loadUser(p);
        }
        log.send("[qEssentialsReloaded] [Metrics] Instantiating metrics...");
        try {
            metrics = new Metrics(this);
            log.send("[qEssentialsReloaded] [Metrics] Successfully instantiated metrics!");
        } catch (IOException e) {
            log.send(LogType.WARN, "[qEssentialsReloaded] [Metrics] Failed to instantiate the metrics!");
        }
        log.send("[qEssentialsReloaded] Configuring tablist messages...");
        TablistUtils.configureMessages();
        log.send("[qEssentialsReloaded] Preloading tab...");
        Main.getTabExecutor().loadTab();
        log.send("[qEssentialsReloaded] Starting tasks...");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AutoMessageTask(), 0L, getConfig().getLong("automessage-delay") * 20);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TablistRefreshTask(), 0L, TabConfigurationImpl.tablistRefreshTime * 20);
        Bukkit.getScheduler().runTaskLaterAsynchronously(this, new MetricsCollector(), 20);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TPSMonitor(), 100L, 1);
        log.send("[qEssentialsReloaded] Configuring help paged map...");
        PaginatorUtils.configureHelp();
        log.send("[qEssentialsReloaded] Configuring enchantment aliases...");
        EnchantmentUtils.configureEnchantments();
        log.send("[qEssentialsReloaded] Loading kits...");
        Main.getKitManager().load();
        log.send("[qEssentialsReloaded] Loading groups...");
        ChatManagerImpl.loadGroups();
        log.send("[qEssentialsReloaded] Loading warps...");
        WarpManagerImpl.loadWarps();
        log.send("[qEssentialsReloaded] Loading bans...");
        BanManagerImpl.loadBans();
        loadTime = System.currentTimeMillis() - startTime;
        log.send("[qEssentialsReloaded] Completed successfuly! (" + loadTime + "ms)");
    }
}
