package me.kavzaq.qEssentialsReloaded.impl;

import com.google.common.collect.Lists;

import java.util.List;

public class KitImpl {

    public static List<KitImpl> list = Lists.newArrayList();
    private final String name;
    private long cooldown;
    private List<String> items;

    public KitImpl(String name) {
        this.name = name;
    }


    public List<KitImpl> getList() {
        return list;
    }


    public String getName() {
        return name;
    }

    public long getCooldown() {
        return cooldown;
    }


    public List<String> getItems() {
        return items;
    }


    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }


    public void setItems(List<String> items) {
        this.items = items;
    }

}
