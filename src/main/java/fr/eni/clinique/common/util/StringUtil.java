package fr.eni.clinique.common.util;

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
public class StringUtil {

    private StringUtil() {
        
    }
    
    /**
     * Convert Null value to blank value.
     * 
     * @param data
     * @return
     */
    public static final String nullToBlank(String data) {
        
        String result = AppConstants.EMPTY;
        
        if(data != null) {
            result = data; 
        }
        
        return result;
    }
    
    public static final Boolean isNull(String str) {
	    Boolean result = true;
    	if(str != null && !str.isEmpty()){
    		result = false;
	    }
    	return result;
    }
    
    public static Timestamp convertStringToTimestamp(String str_date) {
        try {
          DateFormat formatter;
          formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
           // you can change format of date
          Date date = formatter.parse(str_date);
          java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

          return timeStampDate;
        } catch (ParseException e) {
          System.out.println("Exception :" + e);
          return null;
        }
      }
}
