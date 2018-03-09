package fr.eni.clinique.common.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * String utilities.
 * 
 * @author externe
 *
 */
public class SqlUtil {

    private SqlUtil() {
        
    }
    
    /**
     * Check if a SQL resultSet contain a specific column
     * 
     * @param data
     * @return
     */
    public static boolean resultSethasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }
}
