package me.kavzaq.qEssentialsReloaded.commands.aliases;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

public class SunAlias extends CommandImpl {

    public SunAlias() {
        super("sun", "Weather alias", "/sun", "weather.aliases", Lists.newArrayList());

    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        World world = Bukkit.getWorlds().get(0);
        world.setStorm(false);
        world.setThundering(false);
        Util.sendMessage(s, MessagesImpl.WEATHER_SUNNY_SUCCESS.replace("%world%", world.getName()));
    }


}
