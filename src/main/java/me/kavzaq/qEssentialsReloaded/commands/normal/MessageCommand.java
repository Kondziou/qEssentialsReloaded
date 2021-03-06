package me.kavzaq.qEssentialsReloaded.commands.normal;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MessageCommand extends CommandImpl {

    public MessageCommand() {
        super("message", "Sends a message to player", "/message <player>", "message", Arrays.asList("qmessage", "msg", "m"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length < 2) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
            return;
        }
        Player other = Bukkit.getPlayer(args[0]);
        if (other == null) {
            Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
            return;
        }
        if (s.equals(other)) {
            Util.sendMessage(s, MessagesImpl.SAME_PERSON);
            return;
        }
        String message = Util.fixColors(StringUtils.join(args, " ", 1, args.length));
        Main.getMessageData().getMessageContainer().put(s.getName(), other.getName());
        Main.getMessageData().getMessageContainer().put(other.getName(), s.getName());

        Util.sendMessage(s, MessagesImpl.MESSAGE_TO_FORMAT
                .replace("%player%", other.getName())
                .replace("%message%", message));
        Util.sendMessage(other, MessagesImpl.MESSAGE_FROM_FORMAT
                .replace("%player%", s.getName())
                .replace("%message%", message));
    }

}
