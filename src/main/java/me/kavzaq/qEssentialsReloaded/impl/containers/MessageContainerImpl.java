package me.kavzaq.qEssentialsReloaded.impl.containers;

import com.google.common.collect.Maps;

import java.util.HashMap;

public class MessageContainerImpl {

    public static final HashMap<String, String> messageContainer = Maps.newHashMap();

    public HashMap<String, String> getMessageContainer() {
        return messageContainer;
    }

}
