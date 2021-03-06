package me.kavzaq.qEssentialsReloaded.impl.packet;

import com.mojang.authlib.GameProfile;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.json.JSONPacketBuilder;
import me.kavzaq.qEssentialsReloaded.utils.reflections.ReflectionUtils;
import me.kavzaq.qEssentialsReloaded.utils.reflections.packets.PacketEssential;
import me.kavzaq.qEssentialsReloaded.utils.reflections.packets.PacketPlayerInfo;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TabManagerImpl {

    @SuppressWarnings({"unchecked", "rawtypes"})

    public void sendPacket(Player player, GameProfile gp, String slot, String mode) {
        Object cons = ReflectionUtils.getConstructor(PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO);
        Field a = ReflectionUtils.getField(PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO, "a");
        Field b = ReflectionUtils.getField(PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO, "b");

        try {
            a.setAccessible(true);
            a.set(cons, Enum.valueOf((Class<Enum>) PacketEssential.PACKET_PLAY_OUT_PLAYER_INFO$ENUM_PLAYER_INFO_ACTION, mode));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            Main.log.send(e);
        }

        List<Object> ppilist = new ArrayList<>();
        ppilist.add(PacketPlayerInfo.getPlayerInfo(Main.getUserManager().getUser(player), gp, slot));

        try {
            b.setAccessible(true);
            b.set(cons, ppilist);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            Main.log.send(e);
        }

        PacketEssential.sendPacket(player, cons);
    }


    public void sendPacketHeaderFooter(Player player, String header, String footer) {
        Object cons = ReflectionUtils.getConstructor(PacketEssential.PACKET_PLAY_OUT_PLAYER_LIST_HEADER_FOOTER);
        Field a = ReflectionUtils.getField(PacketEssential.PACKET_PLAY_OUT_PLAYER_LIST_HEADER_FOOTER, "a");
        Field b = ReflectionUtils.getField(PacketEssential.PACKET_PLAY_OUT_PLAYER_LIST_HEADER_FOOTER, "b");
        a.setAccessible(true);
        b.setAccessible(true);

        Object headercontent = null;
        Object footercontent = null;
        try {
            headercontent = JSONPacketBuilder.build("{\"text\": \"" + Util.fixColors(header) + "\"}");
            footercontent = JSONPacketBuilder.build("{\"text\": \"" + Util.fixColors(footer) + "\"}");
        } catch (IllegalArgumentException e) {
            Main.log.send(e);
        }


        try {
            a.set(cons, headercontent);
            b.set(cons, footercontent);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            Main.log.send(e);
        }
        PacketEssential.sendPacket(player, cons);
    }

}
