package me.kavzaq.qEssentialsReloaded.utils;

public class TimeUnit {
    
    private TimeUnit() { }
    public static long parseUnit(String unit) {
        String[] split = unit.split("");
        long _default = Integer.valueOf(split[0]) * 60;
        if (_default == 0) return 0;
        switch (split[1]) {
            case "sec":
                return (_default *= 1 / 60) * 1000;
            case "min":
                return (_default *= 1) * 1000;
            case "hour":
                return (_default *= 60) * 1000;
            case "day":
                return (_default *= 60 * 24) * 1000;
            case "week":
                return (_default *= 60 * 24 * 7) * 1000;
            case "month":
                return (_default *= 60 * 30 * 24) * 1000;
            case "year":
                return (_default *= 60 * 30 * 24 * 12) * 1000;
        }
        return 0;
    }
    
}
