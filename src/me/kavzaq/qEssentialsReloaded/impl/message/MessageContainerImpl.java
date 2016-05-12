package me.kavzaq.qEssentialsReloaded.impl.message;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

public class MessageContainerImpl  {

	private static final HashMap<Player, Player> messageContainer = Maps.newHashMap();
	
	public HashMap<Player, Player> getMessageContainer() {
		return messageContainer;
	}

}
