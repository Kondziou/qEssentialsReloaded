package me.kavzaq.qEssentialsReloaded.database;

import me.kavzaq.qEssentialsReloaded.Main;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SQLite implements Database {

    private File file = new File(Main.getInstance().getDataFolder(), "sqlite.db");

    public String driver;
    public String database_url;

    public Connection connection = null;

    public SQLite() {
        // setting variables
        driver = "org.sqlite.JDBC";
        if (!Main.getInstance().getDataFolder().exists()) {
            Main.getInstance().getDataFolder().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Main.log.send(e);
            }
        }
        database_url = "jdbc:sqlite:" + file.getAbsolutePath();

        // searching for driver
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Main.log.send(e);
        }

        try {
            createTables();
        } catch (SQLException e) {
            Main.log.send(e);
        }
    }

    @Override
    public ResultSet executeSelect(PreparedStatement stat) {
        ResultSet result = null;
        try {
            result = stat.executeQuery();
            stat.close();
        } catch (SQLException e) {
            Main.log.send(e);
        }
        return result;
    }

    @Override
    public void executeUpdate(PreparedStatement stat) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            try {
                stat.executeUpdate();
                stat.close();
            } catch (SQLException e) {
                Main.log.send(e);
            }
        });
    }

    @Override
    public Connection getConnection() {
        // connecting
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(database_url);
            } catch (SQLException sqle) {
                Main.log.send(sqle);
            }
        }
        return connection;
    }

    private void createTables() throws SQLException {
        String queryKits =
                "CREATE TABLE IF NOT EXISTS kits (" +
                        "uuid VARCHAR(36) NOT NULL," +
                        "name VARCHAR(255) NOT NULL," +
                        "cooldown SIGNED BIGINT)";

        PreparedStatement statKits = getConnection().prepareStatement(queryKits);
        executeUpdate(statKits);

        String queryHomes =
                "CREATE TABLE IF NOT EXISTS homes (" +
                        "uuid VARCHAR(36)," +
                        "name VARCHAR(255)," +
                        "world VARCHAR(32)," +
                        "x FLOAT(255)," +
                        "y FLOAT(255)," +
                        "z FLOAT(255)," +
                        "pitch FLOAT(255)," +
                        "yaw FLOAT(255))";

        PreparedStatement statHomes = getConnection().prepareStatement(queryHomes);
        executeUpdate(statHomes);

        String queryWarps =
                "CREATE TABLE IF NOT EXISTS warps (" +
                        "name VARCHAR(255)," +
                        "world VARCHAR(32)," +
                        "x FLOAT(255)," +
                        "y FLOAT(255)," +
                        "z FLOAT(255)," +
                        "pitch FLOAT(255)," +
                        "yaw FLOAT(255))";

        try (PreparedStatement statWarps = getConnection().prepareStatement(queryWarps)) {
            statWarps.executeUpdate();
        }

        String queryBans =
                "CREATE TABLE IF NOT EXISTS bans (" +
                        "punisher VARCHAR(36)," +
                        "punished VARCHAR(36)," +
                        "reason SIGNED BIGINT," +
                        "till SIGNED BIGINT)";

        try (PreparedStatement statBans = getConnection().prepareStatement(queryBans)) {
            statBans.executeUpdate();
        }
    }

}
