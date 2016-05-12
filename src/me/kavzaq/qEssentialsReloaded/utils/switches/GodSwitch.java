package me.kavzaq.qEssentialsReloaded.utils.switches;

import me.kavzaq.qEssentialsReloaded.impl.UserImpl;

public class GodSwitch {
    
    private GodSwitch() { }
    
    public static boolean switchGod(UserImpl user) {
        return !user.isGod();
    }

}
