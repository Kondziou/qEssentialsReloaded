package me.kavzaq.qEssentialsReloaded.utils;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.impl.BossBarImpl;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.inventivetalent.bossbar.BossBarAPI.Color;
import org.inventivetalent.bossbar.BossBarAPI.Style;

import java.util.List;

public class ParsingUtils {

    private ParsingUtils() {
    }

    public static BossBarImpl getConfiguredBossBar(Player sender, String syntax, String message) {
        String[] args = syntax.split(";");
        BossBarImpl bar = new BossBarImpl();
        // receiver;color;style;progress;timeout;interval
        bar.setReceiver(args[0]);
        bar.setSender(sender);
        bar.setMessage(message);
        if ((args.length >= 2) && (!args[1].equalsIgnoreCase("*"))) {
            bar.setColor(Color.valueOf(args[1]));
        } else bar.setColor(Color.WHITE);
        if ((args.length >= 3) && (!args[2].equalsIgnoreCase("*"))) {
            bar.setStyle(Style.valueOf(args[2]));
        } else bar.setStyle(Style.PROGRESS);
        if ((args.length >= 4) && (!args[3].equalsIgnoreCase("*"))) {
            bar.setProgress(Float.valueOf(args[3]));
        } else bar.setProgress(1.0f);
        if ((args.length >= 5) && (!args[4].equalsIgnoreCase("*"))) {
            bar.setTimeout(Integer.valueOf(args[4]));
        } else bar.setTimeout(60);
        if ((args.length >= 6) && (!args[5].equalsIgnoreCase("*"))) {
            bar.setInterval(Integer.valueOf(args[5]));
        } else bar.setInterval(2);
        return bar;
    }

    public static ItemStack getParsedItem(String syntax) {
        String[] args = syntax.split(" ");
        // item:data amount name lore1;lore2 enchantment:1;enchantment:2
        ItemStack itemstack = getParsedItem(args[0], Integer.valueOf(args[1]));
        ItemMeta itemmeta = itemstack.getItemMeta();
        if ((args.length >= 3) && (!args[2].equalsIgnoreCase("*"))) {
            itemmeta.setDisplayName(Util.fixColors(args[2].replace("_", " ")));
        }
        if ((args.length >= 4) && (!args[3].equalsIgnoreCase("*"))) {
            String[] loreLines = args[3].split(";");
            List<String> lore = Lists.newArrayList();
            for (String s : loreLines) {
                lore.add(s.replace("_", " "));
            }
            itemmeta.setLore(Util.fixColoredList(lore));
        }
        itemstack.setItemMeta(itemmeta);
        if ((args.length >= 5) && (!args[4].equalsIgnoreCase("*"))) {
            String[] enchantments = args[4].split(";");
            for (String s : enchantments) {
                addEnchant(s, itemstack);
            }
        }
        return itemstack;
    }

    public static void addEnchant(String syntax, ItemStack is) {
        String[] args = syntax.split(":");
        Enchantment ench = EnchantmentUtils.enchants.get(args[0]);
        int power = Integer.valueOf(args[1]);
        is.addUnsafeEnchantment(ench, power);
        return;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getParsedItem(String item, int amount) {
        ItemStack itemR = null;
        try {
            short data;
            String[] split;

            if (!item.contains(":")) {
                split = new String[]{item};
                data = 0;
            } else {
                split = item.split(":");
                data = split[1].length() < 5 && split[1].length() > 0 ? Short.parseShort(split[1]) : 0;
            }

            String namer = split[0];

            if (StringUtils.isNumeric(namer)) {
                int id = namer.length() < 5 && namer.length() > 0 ? Integer.parseInt(namer) : 0;
                itemR = new ItemStack(Material.getMaterial(id), amount, data);
            } else itemR = new ItemStack(Material.matchMaterial(namer.toUpperCase()), amount, data);

        } catch (Exception e) {
            return null;
        }

        return itemR;
    }

}
