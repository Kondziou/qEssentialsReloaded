package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class BroadcastCommand extends CommandImpl {

    private static final StringBuilder localsb = new StringBuilder();

    public BroadcastCommand() {
        super("broadcast", "A global message to server users", "/broadcast <message>", "broadcast", Arrays.asList("bc", "ogloszenie", "qbc"));
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            Util.sendMessage(sender, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        localsb.setLength(0);
        for (String str : args) {
            if (localsb.length() > 0) {
                localsb.append(" ");
            }
            localsb.append(str);
        }
        Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.BROADCAST_STYLE
                .replace("%message%", localsb.toString())));
    }

}
