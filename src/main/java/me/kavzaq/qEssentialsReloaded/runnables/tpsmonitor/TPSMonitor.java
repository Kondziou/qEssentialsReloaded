package me.kavzaq.qEssentialsReloaded.runnables.tpsmonitor;

import me.kavzaq.qEssentialsReloaded.utils.Util;
import net.minecraft.server.v1_10_R1.MinecraftServer;

public class TPSMonitor implements Runnable {

    public static int tps = 0;
    public static long[] ticks = new long[600];

    @Override
    public void run() {
        ticks[(tps % ticks.length)] = System.currentTimeMillis();
        tps += 1;
    }

    public static double getPercentage() {
        return Util.round(100 - (getCurrentTPS() * 5), 2);
    }

    public static double getPercentage(double tps) {
        return Util.round(100 - (tps * 5), 2);
    }

    public static double getCurrentTPS() {
        if (tps < 100) return -1;
        int elapsedTime = (int) (System.currentTimeMillis() -
                ticks[(tps - 1 - 100) % ticks.length]);

        return Util.round(100 / (elapsedTime / 1000.0), 3);
    }

    public static double getAverageTPS(int time) {
        double[] tps = MinecraftServer.getServer().recentTps;
        switch (time) {
            case 1:
                return formatTPS(tps[0]);
            case 5:
                return formatTPS(tps[1]);
            case 15:
                return formatTPS(tps[2]);
        }
        return -1;
    }

    public static double formatTPS(double tps) {
        if (tps > 20.0) return -1;
        return Math.min(Util.round(tps * 100, 3) / 100.0, 20.0);
    }
}
