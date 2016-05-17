package me.kavzaq.qEssentialsReloaded.utils.switches;

import me.kavzaq.qEssentialsReloaded.Main;

public class ChatSwitch {
    
    private ChatSwitch() { }
    
    private static boolean chat = Main.getInstance().getConfig().getBoolean("chat-default-enabled");
    
    public static boolean switchChat() {
        chat = !chat;
        return chat;
    }
    
    public static boolean getChat()
    {
        return chat;
    }

}
