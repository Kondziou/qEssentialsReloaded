package me.kavzaq.qEssentialsReloaded.utils;

import me.kavzaq.qEssentialsReloaded.impl.configuration.MessagesImpl;
import org.bukkit.entity.Player;

public class BooleanUtils {

    private BooleanUtils() {
    }

    public static String getParsedBoolean(boolean bool) {
        return bool ?
                MessagesImpl.BOOLEAN_ON : MessagesImpl.BOOLEAN_OFF;
    }

    public static String getParsedBooleanYesNo(boolean bool) {
        return bool ?
                MessagesImpl.BOOLEAN_YES : MessagesImpl.BOOLEAN_NO;
    }

    public static String getOperatorParsedBoolean(Player player) {
        if (player.hasPermission("'*'") || player.isOp()) {
            return MessagesImpl.BOOLEAN_YES;
        }
        return MessagesImpl.BOOLEAN_NO;
    }

}
