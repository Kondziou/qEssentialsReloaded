package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ReplyCommand extends CommandImpl {

    public ReplyCommand() {
        super("reply", "Replies to a message", "/reply <message>", "reply", Arrays.asList("qreply", "odpowiedz", "r"), true);
    }

    private static final StringBuilder localsb = new StringBuilder();

    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player) s;
        if (args.length == 0) {
            Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        String otherName = Main.getMessageData().getMessageContainer().get(p.getName());
        if (Bukkit.getPlayer(otherName) == null) {
            Util.sendMessage(p, MessagesImpl.REPLY_OFFLINE_OR_NO_CONV);
            return;
        }
        Player other = Bukkit.getPlayer(otherName);
        localsb.setLength(0);
        for (String str : args) {
            if (localsb.length() > 0) {
                localsb.append(" ");
            }
            localsb.append(str);
        }

        String message = Util.fixColors(localsb.toString());
        Main.getMessageData().getMessageContainer().put(p.getName(), other.getName());
        Main.getMessageData().getMessageContainer().put(other.getName(), p.getName());

        Util.sendMessage(p, MessagesImpl.MESSAGE_TO_FORMAT
                .replace("%player%", other.getName())
                .replace("%message%", message));
        Util.sendMessage(other, MessagesImpl.MESSAGE_FROM_FORMAT
                .replace("%player%", p.getName())
                .replace("%message%", message));
    }
}
