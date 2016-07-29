package me.kavzaq.qEssentialsReloaded.utils.json;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.reflections.ReflectionUtils;
import me.kavzaq.qEssentialsReloaded.utils.reflections.packets.PacketEssential;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JSONPacketBuilder {

    private JSONPacketBuilder() {
    }

    public static Object build(String content) {
        Method method = ReflectionUtils.getTypedMethod(
                PacketEssential.ICHAT_BASE_COMPONENT$CHAT_SERIALIZER, "a",
                PacketEssential.ICHAT_BASE_COMPONENT, String.class);

        try {
            return method.invoke(null, content);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Main.log.send(e);
        }
        return null;
    }

}
