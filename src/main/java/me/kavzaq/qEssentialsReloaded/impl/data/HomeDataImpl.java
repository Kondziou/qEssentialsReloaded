package me.kavzaq.qEssentialsReloaded.impl.data;

import org.bukkit.Location;

public class HomeDataImpl {

    private final String name;
    private final Location location;

    public HomeDataImpl(String name, Location location) {
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
