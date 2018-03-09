package fr.eni.clinique.common.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.clinique.AppliTestBLL;
import fr.eni.clinique.common.AppConstants;

/**
 * String utilities.
 * 
 * @author externe
 *
 */
public class LogUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(LogUtil.class);
    private LogUtil() {
        
    }
    
    /**
     * Write log in a static way (=> anywhere in the code)
     * 
     * @param data
     * @return
     */
    /*
    public static void writeError(String val) {
    	
    }
    */
}
