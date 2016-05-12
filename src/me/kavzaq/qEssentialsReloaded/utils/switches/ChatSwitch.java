package me.kavzaq.qEssentialsReloaded.utils.switches;

public class ChatSwitch {
    
    private ChatSwitch() { }
    
    private static boolean chat;
    
    public static boolean switchChat(){
        chat = !chat;
        return chat;
    }
    
    public static boolean getChat()
    {
        return chat;
    }

}
