package me.kavzaq.qEssentialsReloaded.utils;

import org.apache.commons.lang.StringUtils;

public class TimeUnit {
    
    private static final StringBuilder localsb = new StringBuilder();
    private TimeUnit() { }
    public static long parseUnit(String unit) {
        localsb.setLength(0);
        String[] split = unit.split("");
        boolean done = false;
        int index = 0;
        while (!done) {
            if (!StringUtils.isNumeric(split[index])) {
                done = true;
                break;
            } 
            localsb.append(split[index]);
            index++;
        }
        long _default = Integer.valueOf(localsb.toString()) * 60;
        if (_default == 0) return 0;
        switch (split[index]) {
            case "s":
                return (_default *= 1 / 60) * 1000;
            case "m":
                return (_default *= 1) * 1000;
            case "h":
                return (_default *= 60) * 1000;
            case "d":
                return (_default *= 60 * 24) * 1000;
            case "w":
                return (_default *= 60 * 24 * 7) * 1000;
        }
        return 0;
    }
    
}
