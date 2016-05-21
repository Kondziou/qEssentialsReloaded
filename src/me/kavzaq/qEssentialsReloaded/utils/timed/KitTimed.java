package me.kavzaq.qEssentialsReloaded.utils.timed;

import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.data.KitDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.impl.KitImpl;

public class KitTimed {
    
    private KitTimed() { }
    
    public static void takeKit(KitImpl kit, Player p) {
        UserImpl u = Main.getUserManager().getUser(p);
        if (getKitData(kit, p) != null) {
            u.delKit(getKitData(kit, p));
        }
        u.addKit(new KitDataImpl(kit.getName(), System.currentTimeMillis() + kit.getCooldown()));
        
    }
    
    public static KitDataImpl getKitData(KitImpl kit, Player p) {
        UserImpl u = Main.getUserManager().getUser(p);
        KitDataImpl _kit = null;
        if (u.getKits() == null) return null;
        for (KitDataImpl kitData : u.getKits()) {
            if (kitData.getName().equals(kit.getName())) _kit = kitData;
        }
        return _kit;
    }
    
    public static boolean canTake(KitImpl kit, Player p) {
        if (getKitData(kit, p) == null) 
        {
            return true;
        }
        long timeTake = getKitData(kit, p).getCooldown() == null ? 0 : getKitData(kit, p).getCooldown();
        if(timeTake == 0) return true; 
        long timeCurrent = System.currentTimeMillis();
        return timeTake < timeCurrent;
    }
    
    public static String timeRemain(KitImpl kit, Player p) {
        return Util.parseTime(((getKitData(kit, p).getCooldown() == null ? 0 : getKitData(kit, p).getCooldown() - System.currentTimeMillis())) + 1);
    }

}
