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

public class SQLite {

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
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        database_url = "jdbc:sqlite:" + file.getAbsolutePath();
        
        // searching for driver
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        
        try {
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet executeSelect(PreparedStatement stat) {
        ResultSet result = null;
        try {
            result = stat.executeQuery();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
            
        });
    }
     
    public Connection getConnection() {
        // connecting
        if (connection == null) {
            try { 
                connection = DriverManager.getConnection(database_url);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return connection;
    }
    
    private void createTables() throws SQLException {
        String queryUsers = 
                "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name VARCHAR(20) NOT NULL, "
                + "uuid VARCHAR(36) NOT NULL, "
                + "homes VARCHAR(320),"
                + "kits VARCHAR(320))";
        
        PreparedStatement stat = getConnection().prepareStatement(queryUsers);        
        stat.executeUpdate();
        stat.close();
    }

}
