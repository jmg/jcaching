/**
 * Logger
 *
 * TODO Description if available.
 */

package org.jcaching.utils;

/**
 * TODO
 */
public class Logger {
    
    protected static int level = 0;
    
    public static int DEBUG = 1;
    public static int PRODUCTION = 0;
    
    /**
     * Default constructor.
     */
    public Logger() {
    }

    /**
     * TODO
     *
     * @param level TODO
     */
    public static void setLevel(int level) {
        
        Logger.level = level;
    }
    
    /**
     * TODO
     *
     * @return TODO
     */
    public static int getLevel() {
        
        return level;
    }
        
    /**
     * TODO
     *
     * @return TODO
     */
    public String getOrigin() {
        
        return "";
    }
    
    /**
     * TODO
     *
     * @param level TODO
     * @param obj TODO
     */
    public void doLog(int level, Object obj) {
        
        if (Logger.level > PRODUCTION) {
            
            System.out.println(getOrigin() + obj.toString());
        }
    }   
}
