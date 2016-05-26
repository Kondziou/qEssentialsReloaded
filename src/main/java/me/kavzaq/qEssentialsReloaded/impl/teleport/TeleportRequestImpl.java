package me.kavzaq.qEssentialsReloaded.impl.teleport;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;


public class TeleportRequestImpl {
    
    private static final HashMap<Player, Player> lastReceiverByRequester = Maps.newHashMap();
    private static final HashMap<Player, Player> lastRequesterByReceiver = Maps.newHashMap();
    private static final HashMap<Player, Long> lastRequest = Maps.newHashMap();

    
    public HashMap<Player, Player> getRequesterByReceiver() {
        return lastRequesterByReceiver;
    }

    
    public HashMap<Player, Player> getReceiverByRequester() {
        return lastReceiverByRequester;
    }

    
    public HashMap<Player, Long> getLastRequest() {
        return lastRequest;
    }

    
    public void sendRequest(Player requester, Player receiver) {
        lastReceiverByRequester.put(requester, receiver);
        lastRequesterByReceiver.put(receiver, requester);
        lastRequest.put(requester, System.currentTimeMillis());
    }

    
    public void removeRequest(Player requester, Player receiver) {
        lastReceiverByRequester.remove(requester);
        lastRequesterByReceiver.remove(receiver);
        lastRequest.remove(requester);
        
    }

    
    public boolean alreadyRequested(Player requester, Player receiver) {
        return lastRequesterByReceiver.get(receiver) == requester;
    }

    
    public boolean hasRequestPending(Player receiver) {
        return lastRequesterByReceiver.get(receiver) != null;
    }

}
