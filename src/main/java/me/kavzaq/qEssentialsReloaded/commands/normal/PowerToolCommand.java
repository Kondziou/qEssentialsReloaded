package me.kavzaq.qEssentialsReloaded.commands.normal;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PowerToolCommand extends CommandImpl {

    public PowerToolCommand() {
        super("powertool", "Assigning command to an item", "/powertool <command>", "powertool", 
                Arrays.asList("pt", "powert", "qpowertool", "qpt"), true);
    }

    private static final StringBuilder localsb = new StringBuilder();
    
    @Override
    public void onExecute(CommandSender s, String[] args) {
        Player p = (Player)s;
        ItemStack hand = p.getInventory().getItemInMainHand();
        if ((hand == null) || (hand.getType().equals(Material.AIR))) {
            Util.sendMessage(p, MessagesImpl.POWERTOOL_UNKNOWN);
            return;
        }
        ItemMeta im = hand.getItemMeta();
        if (args.length == 0) {
            im.setLore(Lists.newArrayList());
            hand.setItemMeta(im);
            Util.sendMessage(p, MessagesImpl.POWERTOOL_SUCCESS);
            return;
        }
        else {
            localsb.setLength(0);
            for (String str : args) {
                if (localsb.length() > 0) {
                    localsb.append(" ");
                }
            localsb.append(str);
            }
            List<String> lore = im.getLore() == null ? Lists.newArrayList() : im.getLore();
            lore.add("  // PowerTool command:");
            lore.add(localsb.toString());
            im.setLore(lore);
            hand.setItemMeta(im);
            Util.sendMessage(p, MessagesImpl.POWERTOOL_SUCCESS_ASSIGNED
                    .replace("%command%", localsb.toString()));
        }
    }
    
}
