package fr.eni.clinique.common.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.clinique.common.AppConstants;

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
