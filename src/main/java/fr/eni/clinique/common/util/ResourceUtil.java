package fr.eni.clinique.common.util;

import fr.eni.clinique.common.exception.TechnicalException;

public class ResourceUtil {

    private ResourceUtil() {
        
    }
    
    /**
     * Close id not null.
     * 
     * @param closeable
     */
    private static void safeClose(AutoCloseable closeable) {
        if(closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                throw new TechnicalException("Could not close ", e);
            }
        }
    }
    
    /**
     * Close if not null all the autoclosables.
     * 
     * @param closeables The cleaseables to close
     */
    public static void safeClose(AutoCloseable... closeables) {
        for(AutoCloseable autoCloseable : closeables) {
            safeClose(autoCloseable);
        }
    }
    

}
