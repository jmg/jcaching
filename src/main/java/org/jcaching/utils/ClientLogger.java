/**
 * ClientLogger
 *
 * TODO Description if available.
 */

package org.jcaching.utils;

/**
 * TODO
 */
public class ClientLogger extends Logger {      
    
    /**
     * Default constructor.
     */
    public ClientLogger() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrigin() {
        
        return "Client -> ";
    }
    
    /**
     * TODO
     *
     * @param obj TODO
     */
    public static void log(Object obj) {
        
        new ClientLogger().doLog(level, obj);               
    }
}
