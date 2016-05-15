package me.kavzaq.qEssentialsReloaded.listeners;

import com.google.common.collect.Lists;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack hand = p.getInventory().getItemInMainHand();
        if ((hand == null) || (hand.getType().equals(Material.AIR))) {
            return;
        }
        ItemMeta im = hand.getItemMeta();
        List<String> lore = im.getLore() == null ? Lists.newArrayList() : im.getLore();
        boolean isPowered = false;
        for (String command : lore) {
            if (command.contains("// PowerTool command:")) {
                isPowered = true;
            }
        }
        if (!isPowered) {
            return;
        }
        for (String command : lore) {
            if (!command.contains("// PowerTool command:")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
    }
    
}
