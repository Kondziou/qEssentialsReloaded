package me.kavzaq.qEssentialsReloaded.runnables.metrics;

import com.google.common.collect.Lists;
import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.KitImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.impl.data.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.WarpManagerImpl;
import org.mcstats.Metrics.Graph;
import org.mcstats.Metrics.Plotter;

import java.util.List;

public class MetricsCollector implements Runnable {

    // Test
    @Override
    public void run() {
        Graph g = Main.getMetrics().createGraph("qEssentials Graph");
        final List<HomeDataImpl> homes = Lists.newArrayList();
        final List<KitImpl> kits = KitImpl.list;
        for (UserImpl user : Main.getUserManager().getUsers()) {
            for (HomeDataImpl homeData : user.getHomes()) {
                homes.add(homeData);
            }
        }

        g.addPlotter(new Plotter("Warps") {

            @Override
            public int getValue() {
                return WarpManagerImpl.getWarps().size();
            }

        });
        g.addPlotter(new Plotter("Homes") {

            @Override
            public int getValue() {
                return homes.size();
            }

        });
        g.addPlotter(new Plotter("Kits") {

            @Override
            public int getValue() {
                return kits.size();
            }

        });
        Main.getMetrics().start();
    }

}
