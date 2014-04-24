/**
 * ServerLogger
 *
 * TODO Description if available.
 */

package org.jcaching.utils;

/**
 * TODO
 */
public class ServerLogger extends Logger {  
    
    /**
     * Default constructor.
     */
    public ServerLogger() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrigin() {
        
        return "Server -> ";
    }
    
    /**
     * TODO
     *
     * @param obj TODO
     */
    public static void log(Object obj) {
        
        new ServerLogger().doLog(level, obj);               
    }
}
