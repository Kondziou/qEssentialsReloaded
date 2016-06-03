package me.kavzaq.qEssentialsReloaded.database;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Database {

    public ResultSet executeSelect(PreparedStatement stat);
    
    public void executeUpdate(PreparedStatement stat);
     
    public Connection getConnection();
}
