package me.kavzaq.qEssentialsReloaded.impl.data;

public class KitDataImpl {

    private final String name;
    private final long cooldown;
    
    public KitDataImpl(String name, long cooldown) {
        this.name = name;
        this.cooldown = cooldown;
    }

    public String getName() {
        return name;
    }
    
    public Long getCooldown() {
        return cooldown;
    }

}
