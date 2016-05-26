package me.kavzaq.qEssentialsReloaded.impl.containers;

import java.util.HashMap;

import com.google.common.collect.Maps;

public class MessageContainerImpl  {

    public static final HashMap<String, String> messageContainer = Maps.newHashMap();
    
    public HashMap<String, String> getMessageContainer() {
        return messageContainer;
    }

}
