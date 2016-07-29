package me.kavzaq.qEssentialsReloaded.runnables;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class AutoMessageTask implements Runnable {

    private static int currentIndex = 0;

    private static final List<String> messageList =
            Main.getInstance().getConfig().getStringList("automessages");

    @Override
    public void run() {
        if ((messageList.isEmpty()) || (messageList == null)) {
            return;
        }
        if (currentIndex > (messageList.size() - 1)) {
            currentIndex = 0;
        }
        String currentMessage = messageList.get(currentIndex);
        currentIndex++;
        for (Player p : Bukkit.getOnlinePlayers()) {
            Util.sendMessage(p, currentMessage);
        }
        return;
    }

}
