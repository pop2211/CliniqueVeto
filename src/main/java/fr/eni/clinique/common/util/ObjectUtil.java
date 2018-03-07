package fr.eni.clinique.common.util;

import fr.eni.clinique.common.AppConstants;

public class ObjectUtil {
    
    public static final String nullToBlank(String data) {

        String result = AppConstants.EMPTY;

        if(data != null) {
            result = data; 
        }
        
        return result;
    }
    
    public static final String nullToBlank(Integer data) {
    	
        String result = AppConstants.EMPTY;

        if(data != null) {
        	result = data.toString(); 
        }
        
        return result;
    }
    
    /**
     * Check If Not null.
     * 
     * @param element
     */
    public static void checkNotBlank(String element) {
        if(element == null || element.length() == 0) {
            throw new IllegalArgumentException(String.format("%s must not be blank !", element));
        }
    }
    
    /**
     * Check if not blank with message.
     * 
     * @param element
     * @param message
     */
    public static void checkNotBlankWithMessage(String element, String message) {
        if(element == null || element.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    

    
    /**
     * Check If Not null.
     * 
     * @param element
     */
    public static void checkNotNull(Object element) {
        if(element == null) {
            throw new IllegalArgumentException(String.format("Are you kidding me ? NULL is not Permitted in this method"));
        }
    }
    
    /**
     * Check If Not null with message.
     * 
     * @param element
     */
    public static void checkNotNullWithMessage(Object element, String message) {
        if(element == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void checkTimestamp(Object element, String message) {
        if(((String) element).matches("/d{4}-/d{2}-/d{2} /d{2}:/d{2}")) {
            throw new IllegalArgumentException(message);
        }
    }
    
}
