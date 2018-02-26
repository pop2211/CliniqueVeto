package fr.eni.clinique.dal.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.config.AppConfig;

public class MSSQLConnectionFactory {
    
    /**
     * Ths constructor is private because we dont want to instante it from outside.
     */
    private MSSQLConnectionFactory() {
        
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
            
            connection =  DriverManager.getConnection(url, username, password);
            
        } catch (SQLException e) {
            throw new TechnicalException("Could not load MSSQL Connection", e);
        }
        return connection;
    }

}
