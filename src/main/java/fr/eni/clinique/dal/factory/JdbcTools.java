package fr.eni.clinique.dal.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.config.AppConfig;

public class JdbcTools {
    
    /**
     * Ths constructor is private because we dont want to instante it from outside.
     */
    private JdbcTools() {
        
    }
    
    /**
     * Getting new MSSQL COnnection.
     * 
     * @return A connection to the MSSQL DATABASE.
     */
    public static Connection get() {
        Connection connection = null;
        
        try {
            
            String url = AppConfig.get("database.mssql.url");
            String username = AppConfig.get("database.mssql.username");
            String password = AppConfig.get("database.mssql.password");
            
            // get connection from datasource Connection 
            connection =  DriverManager.getConnection(url, username, password);
            
            // wrap the connection with log4jdbc 
            // connection = new net.sf.log4jdbc.ConnectionSpy(connection);
            // now use Connection as normal (but it will be audited by log4jdbc)
            
            
        } catch (SQLException e) {
            throw new TechnicalException("Could not load MSSQL Connection", e);
        }
        return connection;
    }

}
