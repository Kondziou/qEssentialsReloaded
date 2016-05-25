package me.kavzaq.qEssentialsReloaded.impl.configuration;

import java.util.Arrays;
import java.util.List;

public class MessagesImpl {
    
    private MessagesImpl() { }
    
    // General
    public static String LANGUAGE = "en";
    public static String TRANSLATION_AUTHOR = "KaVzaQ";
    public static String NO_PERMISSION = "&4Error: &cYou don't have permission to this command! &7(%permission%)";
    public static String BYPASS_PERMISSION = "&4Error: &cYou can't do this on this player.";
    public static String ONLY_PLAYER = "&rYou can't do this by console.";
    public static String BAD_ARGS = "&4Error: &cCorrect usage: ";
    public static String OFFLINE_PLAYER = "&4Error: &cThis player is offline!";
    public static String OFFLINE_PLAYERS = "&4Error: &cOne of these players are offline!";
    public static String BOOLEAN_ON = "enabled";
    public static String BOOLEAN_OFF = "disabled";
    public static String BOOLEAN_YES = "yes";
    public static String BOOLEAN_NO = "no";
    public static String SAME_PERSON = "&4Error: &cYou can't do this on yourself!";
    public static String SAME_PERSONS = "&4Error: &cYou can't do this on same players!";
    public static String MUST_BE_INT = "&4Error: &cTime must be an integer!";
    
    // Listeners
    public static String CHAT_IS_DISABLED = "&4Error: &cChat is currently disabled!";
    public static String CHAT_SLOWDOWN = "&4Error: &cYou must wait &7%remain%&c to chat!";
    
    // Help command
    public static String HELP_HEADER = "&7#--------------- &cHelp &7[&c%page% &7/&c %pages%&7] ---------------#";
    public static String HELP_INDEX = "&c/%command% &8- &7%description%";
    public static String HELP_UNKNOWN_PAGE = "&4Error: &cUnknown page!";
    
    // GameMode command
    public static String GAMEMODE_UNKNOWN = "&4Error: &cUnknown gamemode!";
    public static String GAMEMODE_SUCCESS = "&7Successfully changed gamemode to: &c%mode%&7!";
    public static String GAMEMODE_OTHER_CHANGED = "&7Player &c%player%&7 changed your gamemode to &c%mode%&7!";
    public static String GAMEMODE_OTHER_SUCCESS = "&7Successfuly changed gamemode to &c%mode%&7 for &c%player%&7!";
    
    // Broadcast command
    public static String BROADCAST_STYLE = "&8[&cqEssentials&8] &7%message% &8[&cReloaded&8]";
    
    // Heal command
    public static String HEAL_SUCCESS = "&7Healed!";
    public static String HEAL_OTHER_HEALED = "&7You've been healed by &c%player%&7!";
    public static String HEAL_OTHER_SUCCESS = "&7You healed &c%player%&7!";

    // Clear command
    public static String CLEAR_SUCCESS = "&7Cleared!";
    public static String CLEAR_OTHER_CLEARED = "&7Your inventory has been cleared by &c%player%&7!";
    public static String CLEAR_OTHER_SUCCESS = "&7Cleared inventory for &c%player%&7!";
    
    // Item command
    public static String ITEM_SUCCESS = "&7You've got: &c%amount% x %item%";
    public static String ITEM_UNKNOWN = "&4Error: &cItem syntax is invalid!";
    public static String ITEM_OTHER_GIVE = "&7You've got: &c%amount% x %item% &7from &c%player%&7!";
    public static String ITEM_OTHER_SUCCESS = "&7Successfully gave &c%player%&7 item &c%item% &7in amount of &c%amount%&7!";
    
    // Teleporting
    public static String TELEPORT_PROCESS = "&7Teleport occurs in &c%delay% &7seconds...";
    public static String TELEPORT_SUCCESS = "&7Successfully teleported!";
    public static String TELEPORT_DENY = "&cTeleport denied.";
    public static String TELEPORT_SAME_PERSON = "&4Error: &cYou cant teleport to yourself!";
    public static String TPA_REQUEST_SENDED = "&7Successfully sended a teleport request to &c%player%&7!";
    public static List<String> TPA_REQUEST_GOT = Arrays.asList(
            "&7Player &c%player% &7want to teleport to you!",
            "&7Use &c/tpaccept &7to accept.",
            "&7Use &c/tpdeny &7to deny.",
            "&7You have &c60 seconds &7to confirm!");
    public static String TPACCEPT_ACCEPTED = "&7Successfully accepted teleport request to &c%player%&7!";
    public static String TPACCEPT_OTHER_ACCEPTED = "&7Your request has been accepted by &c%player%&7!";
    public static String TELEPORT_NO_REQUEST = "&4Error: &cYou havent got any requests!";
    public static String TPDENY_DENIED = "&7Successfully denied teleport request to &c%player%&7!";
    public static String TPDENY_OTHER_DENIED = "&7Your request has been denied by &c%player%&7!";
    public static String TPA_ALREADY_REQUESTED = "&4Error: &cYou requested this player already!";
    public static String TPA_HAS_REQUEST_PENDING = "&4Error: &cThis person has a pending request. Be patient.";
    public static String TP_ALL_SUCCESS = "&7Teleported every person on server to you.";
    public static String TP_FROM_TO_SUCCESS = "&7Successfully teleported a &c%player_from%&7 to &c%player_to%";
    public static String TP_TELEPORTED = "&7You've been teleported by &c%player%&7!";
    
    // Back command
    public static String BACK_SUCCESS = "&7Successfully teleported to last location.";
    public static String BACK_OTHER = "&7Successfully teleported to &c%player%&7 last location!";
    
    // Feed command
    public static String FEED_SUCCESS = "&7Yummy!";
    public static String FEED_OTHER_FEEDED = "&7You've been feed by &c%player%&7!";
    public static String FEED_OTHER_SUCCESS = "&7Successfully feed &c%player%&7!";
    
    // Fly command
    public static String FLY_SWITCHED = "&7Successfully switched &cflying&7 to &c%mode%";
    public static String FLY_OTHER_SWITCHED = "&7Player &c%player%&7 changed your &cnflying&7 to &c%mode%";
    public static String FLY_OTHER_SUCCESS = "&7Successfully switched &cflying&7 for &c%player%&7 to &c%mode%";
    
    // God command
    public static String GOD_SWITCHED = "&7Successfully switched &cgodmode&7 to &c%mode%";
    public static String GOD_OTHER_ACTIVATED = "&7Player &c%player%&7 changed your &cngodmode&7 to &c%mode%";
    public static String GOD_OTHER_SUCCESS = "&7Successfully switched &cgodmode&7 for &c%player%&7 to &c%mode%";
    
    // Setspawn command
    public static String SETSPAWN_SUCCESS = "&7Successfully set a spawnpoint! &7World: &c%world%, &7Coords: &c%coords%";

    // Spawn command
    public static String SPAWN_OTHER_SUCCESS = "&7Successfully teleported player &c%player%&7 to spawnpoint!";
    public static String SPAWN_OTHER_TELEPORTED = "&7You've been teleported to spawnpoint by &c%player%&7!";
    
    // Chat command
    public static String CHAT_DISABLED = "&cChat has been disabled by &7%player%&c!";
    public static String CHAT_ENABLED = "&aChat has been enabled by &7%player%&a!";
    public static String CHAT_CLEARED = "&eChat has been cleared by &7%player%&e!";
    
    // Changename command
    public static String CHANGENAME_BAD_ITEM = "&4Error: &cInvalid item!";
    public static String CHANGENAME_SUCCESS = "&7Successfully changed item name to &c%name%&7!";
    
    // Weather command
    public static String WEATHER_SUNNY_SUCCESS = "&7Successfully switched weather to &csunny&7! (&c%world%&7)";
    public static String WEATHER_THUNDER_SUCCESS = "&7Successfully switched weather to &crainy&7! (&c%world%&7)";
    
    // Time command
    public static String TIME_DAY_SUCCESS = "&Successfully switched time to &cday&7! (&c%world%&7)";
    public static String TIME_NIGHT_SUCCESS = "&7Successfully swtiched time to &cnight&7! (&c%world%&7)";
    public static String TIME_OWN_SUCCESS = "&7Successfully switched time to &c%time%&7! (&c%world%&7)";
    
    // List command
    public static String LIST_HEADER = "&7Online players: &c%players%&7/&c%maxplayers%&7!";
    public static String LIST_INDEX = "&7Players: &c%players%";
    
    // World command
    public static List<String> WORLD_LIST_HEADER = Arrays.asList(
            "&7Use &c/world <world>&7 to select world.",
            "&7Avaiable worlds:");
    public static String WORLD_INDEX = "&7  * &c%world%";
    public static String WORLD_NOT_EXISTS = "&4Error: &cUnknown world!";
    public static String WORLD_BLOCKED = "&4Error: &cThis world is blocked on this server!";
    
    // WhoIs command
    public static List<String> WHOIS_INFO = Arrays.asList(
            "&7Information about &c%player%&7:",
            "&7  UUID: &c%uuid%",
            "&7  IP Address: &c%addressIp%",
            "&7  Global perm.: &c%isGlobalAdmin%",
            "&7  Gamemode: &c%mode%",
            "&7  Flyies: &c%flying%", 
            "&7  Food: &c%food%/10",
            "&7  Health: &c%health%/10",
            "&7  Location: &c%location%", 
            "&7  Godmode: &c%isGod%", 
            "&7  Homes:",
            "%homes%");
    
    public static String WHOIS_HOMES_INDEX = "&7  * &c%home% &7: &c%location%";
    
    // Reload command
    public static String RELOAD_SUCCESS = "&aSuccessfully reloaded qEssentialsReloaded v%version%";
    
    // SetHome command
    public static String SETHOME_MAX = "&4Error: &cYou have maximum amount of homes!";
    public static String SETHOME_HAS_THIS_NAME = "&4Error: &cYou already have that home!";
    public static String SETHOME_SUCCESS = "&7Successfully added new home &c%home%&7!";
    public static String SETHOME_INVALID = "&4Error: &cInvalid home name! (Its too short, or contains invalid chars)";
    
    // Home command
    public static String HOME_UNKNOWN = "&4Error: &cUnknown home!";
    public static String HOME_LIST_HEADER = "&7Home list: ";
    public static String HOME_SUCCESS = "&7Teleported to home &c%home%&7!";
    
    // Delhome command
    public static String DELHOME_UNKNOWN = "&4Error: &cUnknown home!";
    public static String DELHOME_SUCCESS = "&7Successfully removed home &c%home%&7!";
    
    // Tphere command
    public static String TPHERE_SUCCESS = "&7Successfully teleported player &c%player%&7 to you!";
    
    // Message command
    public static String MESSAGE_TO_FORMAT = "&7[&cJa&7 -> &c%player%&7] &c%message%";
    public static String MESSAGE_FROM_FORMAT = "&7[&c%player% &7-> &cJa&7] &c%message%";
    
    // Reply command
    public static String REPLY_OFFLINE_OR_NO_CONV = "&4Error: &cPlayer is offline or last conversation didnt exist!";
    
    // Kit command
    public static String KIT_SUCCESS = "&7You've got kit &c%name%&7!";
    public static String KIT_COOLDOWN = "&4Error: &cYou must wait &7%cooldown%&c before taking this kit again!";
    public static String KIT_LIST = "&7Available kits: &c";
    public static String KIT_UNKNOWN = "&4Error: &cInvalid kit!";
    
    // Enchant command
    public static String ENCHANT_UNKNOWN = "&4Error: &cUnknown enchantment!";
    public static String ENCHANT_SUCCESS = "&7Successfully enchanted an item on &c%enchantment%:%power%&7!";
    public static String ENCHANT_NULL_ITEM = "&4Error: &cInvalid item in main hand!";
    
    // Helpop command
    public static String HELPOP_COOLDOWN = "&4Error: &cYou must wait &7%cooldown%&c before sending next helpop message!";
    public static String HELPOP_FORMAT = "&8[&4HELPOP&8] &8<&c%player%&8> &c%message%";
    
    // Head command
    public static String HEAD_SUCCESS = "&7You've got a head of &c%player%&7!";
    public static String HEAD_NAME = "&7Player head: &c%player%";
    
    // Kick command
    public static String KICK_BROADCAST = "&cKick &8-> &7Player &c%player% &7has been kicked for &c%reason%&7!";
    public static String KICK_DEFAULT_REASON = "&cYou've just got kicked from the server!";
    public static String KICK_FORMAT = "&cYou've been kicked for: %reason%";
    public static String KICK_TEMPBAN = "&cYou must wait &7%cooldown% &cbefore joining again!";
    
    // Hat command
    public static String HAT_UNKNOWN = "&4Error: &cInvalid item!";
    public static String HAT_SUCCESS = "&7Successfully set a new hat!";
    
    // Gc command
    public static List<String> GARBAGECOLLECTOR_INFO = Arrays.asList(
            "&7Server informations:",
            "  &7Performance: ",
            "     &7Current TPS: &c%tps% &7(&c%percentage% overload&7)",
            "     &7Average TPS: ",
            "        &7last 1 minute: &c%1mAvgTPS% &7(&c%1mAvgPercentage% overload&7)",
            "        &7last 5 minutes: &c%5mAvgTPS% &7(&c%5mAvgPercentage% overload&7)", 
            "        &7last 15 minutes: &c%15mAvgTPS% &7(&c%15mAvgPercentage% overload&7)",
            "  &7Uptime: &c%uptime%",
            "  &7Avaiable cores: &c%cores%",
            "  &7System: &c%os%",
            "  &7Java: &c%java%",
            "  &7Max memory: &c%maxMemory%",
            "  &7Total memory: &c%totalMemory%", 
            "  &7Free memory: &c%freeMemory%", 
            "  &7Worlds:", 
            "%worlds%");
    public static String GARBAGECOLLECTOR_WORLD_FORMAT = "&c    %world%&7 -> &c%chunks% &7chunks, &c%objects% &7objects, &c%tiles% &7tiles.";
    
    // Repair command
    public static String REPAIR_UNKNOWN = "&4Error: &cInvalid item!";
    public static String REPAIR_SUCCESS = "&7Successfully repaired an item!";
    public static String REPAIR_ALL_SUCCESS = "&7Successfully repaired whole inventory!";
    public static String REPAIR_ARMOR_SUCCESS = "&7Successfully repaired your armor!";
    
    // Displayname command
    public static String DISPLAYNAME_SUCCESS = "&7Successfully changed displayname to &c%displayname%";
    public static String DISPLAYNAME_RESET = "&7Successfully reseted your displayname.";
    public static String DISPLAYNAME_OTHER_RESET_RESETED = "&7Your displayname has been reseted by &c%player%&7!";
    public static String DISPLAYNAME_OTHER_RESET_SUCCESS = "&7Successfully reseted a displayname of &c%player%&7!";
    public static String DISPLAYNAME_OTHER_SUCCESS = "&7Successfully set a displayname of &c%player%&7 to &c%displayname%&7!";
    public static String DISPLAYNAME_OTHER_CHANGED = "&7Your displayname has been set to &c%displayname% &7by &c%player%&7!";
    
    // Powertool command
    public static String POWERTOOL_UNKNOWN = "&4Error: &cInvalid item.";
    public static String POWERTOOL_SUCCESS_ASSIGNED = "&7Successfully added command &c%command% &7to this item.";
    public static String POWERTOOL_SUCCESS = "&7Successfully reseted commands on this item.";
    
    // TpPos command
    public static String TPPOS_SUCCESS = "&7Successfully teleported to specific coords: &c%coords%";
    public static String TPPOS_COORDS_INVALID = "&4Error: &cInvalid coords!";
    public static String TPPOS_OTHER_SUCCESS = "&7Player &c%player%&7 has been teleported to  &c%coords%";
    public static String TPPOS_OTHER_TELEPORTED = "&7You've been teleported to &c%coords%&7 by &c%player%";
    
    // Kill command
    public static String KILL_SUCCESS = "&7Killed!";
    public static String KILL_OTHER_SUCCESS = "&7Successfully killed a &c%player%&7!";
    public static String KILL_OTHER_KILLED = "&7You've been killed by &c%player%&7!";
    
    // Warp command
    public static String WARP_LIST = "&7Avaiable warps: &c";
    public static String WARP_SUCCESS = "&7You've been teleported to warp &c%warp%";
    public static String WARP_UNKNOWN = "&4Error: &cThat warp doesnt exist!";
    
    // Setwarp command
    public static String SETWARP_EXISTS = "&4Error: &cThat warp already exists!";
    public static String SETWARP_SUCCESS = "&7Successfully added a warp &c%warp%&7!";
    public static String SETWARP_INVALID = "&4Error: &cWarp name is invalid! (Its too short, or contains invalid chars)";
    
    // Delwarp command
    public static String DELWARP_UNKNOWN = "&4Error: &cThat warp doesnt exist!";
    public static String DELWARP_SUCCESS = "&7Sucessfully removed a warp &c%warp%&7!";
    
    // Ban command
    public static String BAN_BROADCAST = "&cBan &8-> &7Player &c%player% &7has been banned permamently for &c%reason%&7!";
    public static String BAN_SUCCESS = "&7Successfully banned player &c%player%&7!";
    
    // Global ban
    public static String BAN_PERMAMENT = "Permament";
    public static String BAN_NO_REASON = "No reason";
    public static List<String> BAN_FORMAT = Arrays.asList(
            "&cYou're banned from this server!",
            "&cReason: %reason%",
            "&cPunisher: %player%",
            "&cTill: %till%");
    
    // Tempban command
    public static String TEMPBAN_BROADCAST = "&cTempban &8-> &7Player &c%player%&7 has been banned till &c%till%&7 for &c%reason%&7!";
    public static String TEMPBAN_SUCCESS = "&7Successfully temporary banned player &c%player%&7!";
    
    // Unban command
    public static String UNBAN_BROADCAST = "&cUnban &8-> &7Player &c%player% &7has been unbanned!";
    public static String UNBAN_SUCCESS = "&7Successfully unbaned player &c%player%&7!";
    public static String UNBAN_UNKNOWN = "&4Error: &cPlayer is already unbanned!";
    
    // Commands desc
    public static String BACK_DESC = "Teleports to last location";
    public static String BROADCAST_DESC = "Global broadcast";
    public static String CHAT_DESC = "Chat management";
    public static String CLEARINV_DESC = "Equipment clears";
    public static String DELHOME_DESC = "Removes existing home";
    public static String FEED_DESC = "Feeds someone";
    public static String FLY_DESC = "Switches fly mode";
    public static String GAMEMODE_DESC = "Game mode management";
    public static String GOD_DESC = "Switches god mode";
    public static String HEAL_DESC = "Heals someone";
    public static String HELP_DESC = "Available commands";
    public static String HOME_DESC = "Teleports to an existing home";
    public static String ITEM_DESC = "Gives an item";
    public static String LIST_DESC = "Shows online player list";
    public static String MESSAGE_DESC = "Sends a message to player";
    public static String REPLY_DESC = "Replies to a message";
    public static String SETHOME_DESC = "Adds new home";
    public static String SETSPAWN_DESC = "Sets spawnpoint";
    public static String SPAWN_DESC = "Teleports to an spawnpoint";
    public static String TIME_DESC = "Time manipulation";
    public static String TP_ACC_DESC = "Accepts a teleport request";
    public static String TPA_DESC = "Sends a teleport request";
    public static String TP_DESC = "Teleport management";
    public static String TP_DENY_DESC = "Denies a teleport request";
    public static String WEATHER_DESC = "Weather manipulation";
    public static String WHOIS_DESC = "Player informations";
    public static String WORLD_DESC = "Teleporting between worlds";
    public static String KIT_DESC = "Kit management";
    public static String GIVE_DESC = "Gives an item to someone";
    public static String ENCHANT_DESC = "Enchants an item";
    public static String INVSEE_DESC = "Opens an equipments of other players";
    public static String HELPOP_DESC = "Message to admins";
    public static String HEAD_DESC = "Gives a player head";
    public static String ENDERCHEST_DESC = "Enderchest management";
    public static String GARBAGECOLLECTOR_DESC = "Server informations";
    public static String REPAIR_DESC = "Repairing management";
    public static String KICK_DESC = "Kicks a player";
    public static String HAT_DESC = "Sets a new head-item";
    public static String DISPLAYNAME_DESC = "Displayname management";
    public static String POWERTOOL_DESC = "Sets a command to item";
    public static String TPHERE_DESC = "Teleports player to you";
    public static String TPPOS_DESC = "Teleports to specific coords";
    public static String RELOAD_DESC = "Reloads plugin";
    public static String KILL_DESC = "Kills a player";
    public static String WARP_DESC = "Teleports to a warp";
    public static String SETWARP_DESC = "Adds new warp";
    public static String DELWARP_DESC = "Removes existing warp";
    public static String BAN_DESC = "Bans a player";
    public static String UNBAN_DESC = "Pardons a player";
    public static String TEMPBAN_DESC = "Temp bans player";
    
    public static String TIMEPARSE_DAY = " day ";
    public static String TIMEPARSE_DAYS = " days ";
    public static String TIMEPARSE_HOUR = " hour ";
    public static String TIMEPARSE_HOURS = " hours ";
    public static String TIMEPARSE_HOURS_2 = " hours ";
    public static String TIMEPARSE_MINUTE = " minute ";
    public static String TIMEPARSE_MINUTES = " minutes ";
    public static String TIMEPARSE_MINUTES_2 = " minutes ";
    public static String TIMEPARSE_SECOND = " second ";
    public static String TIMEPARSE_SECONDS = " seconds ";
    public static String TIMEPARSE_SECONDS_2 = " seconds ";
    
}
