/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import me.kavzaq.qEssentialsReloaded.impl.BossBarImpl;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.ParsingUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BossBarCommand extends CommandImpl { 

    private static final StringBuilder localsb = new StringBuilder();
    
    public BossBarCommand() {
        super("bossbar", "Sends a message on bossbar", "/bossbar <<receiver>;[color/*];[style/*];[progress/*];[timeout/*];[interval/*]> <message>", "bossbar", 
                Arrays.asList("bb", "bbar", "bossb", "bar", "boss"));
    }

    @Override
    public void onExecute(CommandSender s, String[] args) {
        if (args.length <= 1) {
            Util.sendMessage(s, MessagesImpl.BAD_ARGS + this.getUsage());
            return;
        }
        if (!(s instanceof Player)) {
            Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
            return;
        }
        Player p = (Player)s;
        
        String message = null;
        BossBarImpl bar = null;
        try {
            String data = args[0].split(" ")[0];
            message = StringUtils.join(args, " ", 1, args.length);
            bar = ParsingUtils.getConfiguredBossBar(p, data, message);
            bar.show();
        } catch (Exception e) {
            Util.sendMessage(p, "&cAn error occured with executing this syntax. Check your spelling!");
            return;
        }
        
        for (String str : MessagesImpl.BOSSBAR_SUCCESS) {
            Util.sendMessage(p, str
                    .replace("%receiver%", bar.getReceiver())
                    .replace("%message%", Util.fixColors(message))
                    .replace("%color%", bar.toStringColor())
                    .replace("%style%", bar.getStyle().toString())
                    .replace("%progress%", String.valueOf(bar.getProgress()))
                    .replace("%timeout%", String.valueOf(bar.getTimeout()))
                    .replace("%interval%", String.valueOf(bar.getInterval())));
        }
    }
    
    
    
}
