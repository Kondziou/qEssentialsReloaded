package me.kavzaq.qEssentialsReloaded.impl;

import org.bukkit.Location;

public class WarpImpl {
    
    private final String name;
    private final Location location;
    
    public WarpImpl(String name, Location location) {
        this.name = name;
        this.location = location;
    }
    
    public String getName() {
        return name;
    }
    
    public Location getLocation() {
        return location;
    }
}
