package me.kavzaq.qEssentialsReloaded.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import me.kavzaq.qEssentialsReloaded.Main;

public class MySQL implements Database {

    private final String user, pass;
    
    public String driver;
    public String database_url;
    
    public Connection connection = null;
    
    public MySQL(String host, String user, String pass, String name) {
        this(host, 3306, user, pass, name);
    }
    
    public MySQL(String host, int port, String user, String pass, String name) {
        this.user = user;
        this.pass = pass;
        // setting variables
        driver = "com.mysql.jdbc.Driver";
        database_url = "jdbc:mysql://" + host + ":" + port + "/" + name;
        
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
    
    public void executeUpdate(PreparedStatement stat) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {

            @Override
            public void run() {
                try {
                    stat.executeUpdate();
                    stat.close();
                } catch (SQLException e) {
                    Main.log.send(e);
                }
            }
            
        });
    }
     
    public Connection getConnection() {
        // connecting
        if (connection == null) {
            try { 
                connection = DriverManager.getConnection(database_url, user, pass);
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
        
        PreparedStatement statWarps = getConnection().prepareStatement(queryWarps);        
        statWarps.executeUpdate();
        statWarps.close();
        
        String queryBans = 
                "CREATE TABLE IF NOT EXISTS bans (" +
                "punisher VARCHAR(36)," +
                "punished VARCHAR(36)," +
                "reason SIGNED BIGINT," +
                "till SIGNED BIGINT)";
        
        PreparedStatement statBans = getConnection().prepareStatement(queryBans);        
        statBans.executeUpdate();
        statBans.close();
    }

}
