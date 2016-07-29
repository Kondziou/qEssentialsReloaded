package me.kavzaq.qEssentialsReloaded.commands.aliases;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

public class DayAlias extends CommandImpl {

    public DayAlias() {
        super("day", "Time alias", "/day", "time.aliases", Lists.newArrayList());

    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        World world = Bukkit.getWorlds().get(0);
        world.setTime(2500L);
        Util.sendMessage(s, MessagesImpl.TIME_DAY_SUCCESS.replace("%world%", world.getName()));
    }

}
