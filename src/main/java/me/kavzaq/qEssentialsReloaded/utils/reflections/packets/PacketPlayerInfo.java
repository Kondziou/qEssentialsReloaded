package me.kavzaq.qEssentialsReloaded.utils.reflections.packets;

import com.mojang.authlib.GameProfile;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.impl.configuration.TabConfigurationImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.json.JSONPacketBuilder;
import me.kavzaq.qEssentialsReloaded.utils.reflections.ReflectionUtils;
import net.minecraft.server.v1_10_R1.EnumGamemode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PacketPlayerInfo {

    private PacketPlayerInfo() {
    }

    private static final Object PACKET_PLAY_OUT_PLAYER_INFO_CONSTRUCTOR =
            ReflectionUtils.getConstructor(PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Object getPlayerInfo(UserImpl user, GameProfile gp, String slot) {
        Constructor<?> cons = null;
        try {
            cons = PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO$PLAYER_INFO_DATA
                    .getDeclaredConstructor(PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO, GameProfile.class, int.class,
                            EnumGamemode.class, PacketEssential.ICHAT_BASE_COMPONENT);
        } catch (NoSuchMethodException | SecurityException e) {
            Main.log.send(e);
        }
        try {
            return cons.newInstance(PACKET_PLAY_OUT_PLAYER_INFO_CONSTRUCTOR,
                    gp,
                    TabConfigurationImpl.tablistPing,
                    EnumGamemode.SURVIVAL,
                    JSONPacketBuilder.build("{\"text\": \"" + Util.fixColors(slot) + "\"}"));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            Main.log.send(e);
        }
        return null;
    }

}
