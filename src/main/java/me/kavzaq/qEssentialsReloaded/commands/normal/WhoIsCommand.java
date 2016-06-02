package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.BooleanUtils;
import me.kavzaq.qEssentialsReloaded.utils.ListingUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.OfflinePlayer;

public class WhoIsCommand extends CommandImpl {

    public WhoIsCommand() {
        super("whois", "Returns informations about player", "/whois [player]", "whois", Arrays.asList("qwhois", "ktoztojest"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length >= 2) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        if (args.length == 0) {
            if (!(s instanceof Player)) {
                Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
                return;
            }
            Player p = (Player)s;
            UserImpl u = Main.getUserManager().getUser(p);
            OfflinePlayer offp = Bukkit.getOfflinePlayer(p.getUniqueId());
            
            String state = offp.isOnline() ? " (online)" : " (offline)";
            
            for (String str : MessagesImpl.WHOIS_INFO) {
                Util.sendMessage(p, str
                        .replace("%player%", p.getName())
                        .replace("%uuid%", p.getUniqueId().toString())
                        .replace("%addressIp%", p.getAddress().getAddress().getHostAddress())
                        .replace("%isGlobalAdmin%", BooleanUtils.getOperatorParsedBoolean(p))
                        .replace("%mode%", p.getGameMode().toString().toLowerCase())
                        .replace("%flying%", BooleanUtils.getParsedBooleanYesNo(p.isFlying()))
                        .replace("%food%", String.valueOf((p.getFoodLevel() / 2)))
                        .replace("%health%", String.valueOf((p.getHealth() / 2)))
                        .replace("%location%", 
                                "x" + Util.round(p.getLocation().getX(), 3) +
                                ", y" + Util.round(p.getLocation().getY(), 3) +
                                ", z" + Util.round(p.getLocation().getZ(), 3))
                        .replace("%isGod%", BooleanUtils.getParsedBooleanYesNo(u.isGod()))
                        .replace("%homes%", ListingUtils.getHomeList(p))
                        .replace("%lastSeen%", Util.parseTime(System.currentTimeMillis() - offp.getLastPlayed()).replace(Util.parseTime(System.currentTimeMillis()), MessagesImpl.WHOIS_NO_INFORMATION) + state)
                        .replace("%firstSeen%", Util.parseTime(System.currentTimeMillis() - offp.getFirstPlayed())));
            }
        }
        else if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                OfflinePlayer otheroffp = Bukkit.getOfflinePlayer(args[0]);
                for (String str : MessagesImpl.OFFLINE_WHOIS_INFO) {
                    Util.sendMessage(s, str
                        .replace("%player%", otheroffp.getName())
                        .replace("%uuid%", otheroffp.getUniqueId().toString())
                        .replace("%lastSeen%", Util.parseTime(System.currentTimeMillis() - otheroffp.getLastPlayed()).replace(Util.parseTime(System.currentTimeMillis()), MessagesImpl.WHOIS_NO_INFORMATION))
                        .replace("%firstSeen%", Util.parseTime(System.currentTimeMillis() - otheroffp.getFirstPlayed()).replace(Util.parseTime(System.currentTimeMillis()), MessagesImpl.WHOIS_NO_INFORMATION)));
                }
                return;
            }
            Player other = Bukkit.getPlayer(args[0]);
            if (s.equals(other)) {
                Util.sendMessage(s, MessagesImpl.SAME_PERSON);
                return;
            }
            UserImpl otheru = Main.getUserManager().getUser(other);
            OfflinePlayer otheroffp = Bukkit.getPlayer(other.getUniqueId());
            
            String state = otheroffp.isOnline() ? " (online)" : " (offline)";
            
            for (String str : MessagesImpl.WHOIS_INFO) {
                Util.sendMessage(s, str
                        .replace("%player%", other.getName())
                        .replace("%uuid%", other.getUniqueId().toString())
                        .replace("%addressIp%", String.valueOf(other.getAddress().getAddress().getHostAddress()))
                        .replace("%isGlobalAdmin%", BooleanUtils.getOperatorParsedBoolean(other))
                        .replace("%mode%", other.getGameMode().toString().toLowerCase())
                        .replace("%flying%", BooleanUtils.getParsedBooleanYesNo(other.isFlying()))
                        .replace("%food%", String.valueOf((other.getFoodLevel() / 2)))
                        .replace("%health%", String.valueOf((other.getHealth() / 2)))
                        .replace("%location%", 
                                "x" + Util.round(other.getLocation().getX(), 3) +
                                ", y" + Util.round(other.getLocation().getY(), 3) +
                                ", z" + Util.round(other.getLocation().getZ(), 3))
                        .replace("%isGod%", BooleanUtils.getParsedBooleanYesNo(otheru.isGod()))
                        .replace("%homes%", ListingUtils.getHomeList(other))
                        .replace("%lastSeen%", Util.parseTime(System.currentTimeMillis() - otheroffp.getLastPlayed()).replace(Util.parseTime(System.currentTimeMillis()), MessagesImpl.WHOIS_NO_INFORMATION) + state)
                        .replace("%firstSeen%", Util.parseTime(System.currentTimeMillis() - otheroffp.getFirstPlayed())));
                
            }
        }
        
    }

}
