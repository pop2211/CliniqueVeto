package fr.eni.clinique.config;

import java.io.IOException;
import java.util.Properties;

import fr.eni.clinique.common.AppConstants;
import fr.eni.clinique.common.exception.TechnicalException;
import fr.eni.clinique.common.util.ObjectUtil;


/**
 * App Config.
 * 
 * @author DEFORTESCU
 *
 */
public class AppConfig {
    
    private static final Properties PROPERTIES = new Properties();
    
    /**
     * We Want to load at App startup, no LazyLodaing.
     */
    static {

        try {
            PROPERTIES.load(AppConfig.class.getClassLoader().getResourceAsStream(AppConstants.APP_PROPERTIES_NAME));
            
        } catch (IOException e) {
            throw new TechnicalException("Could not load properties", e);
        }
    }
    
    /**
     * Constructor is private because we dont want to instantiate it from outside.
     */
    private AppConfig() {
        
    }
    
    /**
     * 
     * @param key The Property.
     * 
     * @return The Value of the property.
     * 
     */
    public static String get(String key) {
        
        ObjectUtil.checkNotBlank(key);
        
        return PROPERTIES.getProperty(key);
    }
}
