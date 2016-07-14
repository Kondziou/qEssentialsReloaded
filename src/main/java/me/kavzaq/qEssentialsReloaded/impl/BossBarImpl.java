/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kavzaq.qEssentialsReloaded.impl;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.chat.TextComponent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;
import org.inventivetalent.bossbar.BossBarAPI.Color;
import org.inventivetalent.bossbar.BossBarAPI.Style;

public class BossBarImpl {
    
    private Player sender;
    private String receiver;
    private String message;
    private Color color;
    private Style style;
    private float progress;
    private int timeout;
    private int interval;
    
    public BossBarImpl() { }
    
    public void show() {
        // Receiver setting
        Collection<Player> players = new ArrayList<>();
        if (receiver.equalsIgnoreCase("broadcast")) {
            players = (Collection<Player>)Bukkit.getOnlinePlayers();
        } else {
            if (Bukkit.getPlayer(receiver) != null) {
                Player receiverPlayer = Bukkit.getPlayer(receiver);
                players.add(receiverPlayer);
                receiver = receiverPlayer.getName();
            }
            else {
                receiver = "broadcast";
            }
        }
        // Creating a bossbar using BossBarAPI by inventivetalent
        BossBar bar = BossBarAPI.addBar(sender,
            new TextComponent(Util.fixColors(message)), 
            color, style, progress, timeout, interval);
        for (Player p : players) {
            bar.addPlayer(p);
        }
    }
    
    public String toStringColor() {
        switch (color) {
            case BLUE:
                return Util.fixColors("&1BLUE");
            case GREEN:
                return Util.fixColors("&2GREEN");
            case PINK:
                return Util.fixColors("&dPINK");
            case PURPLE:
                return Util.fixColors("&5PURPLE");
            case RED:
                return Util.fixColors("&4RED");
            case WHITE:
                return Util.fixColors("&rWHITE");
            case YELLOW:
                return Util.fixColors("&eYELLOW");
            default:
                break;
        }
        return null;
    }
    
    public BossBarImpl setSender(Player sender) {
        this.sender = sender;
        return this;
    }
    
    public BossBarImpl setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }
    
    public BossBarImpl setMessage(String message) {
        this.message = message;
        return this;
    }
    
    public BossBarImpl setColor(Color color) {
        this.color = color;
        return this;
    }
    
    public BossBarImpl setStyle(Style style) {
        this.style = style;
        return this;
    }
    
    public BossBarImpl setProgress(float progress) {
        this.progress = progress;
        return this;
    }
    
    public BossBarImpl setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }
    
    public BossBarImpl setInterval(int interval) {
        this.interval = interval;
        return this;
    }
    
    public Player getSender() {
        return sender;
    }
    
    public String getReceiver() {
        return receiver;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Style getStyle() {
        return style;
    }
    
    public float getProgress() {
        return progress;
    }
    
    public int getTimeout() {
        return timeout;
    }
    
    public int getInterval() {
        return interval;
    }
}
