/**
 * Storage
 *
 * TODO Description if available.
 */

package org.jcaching.server;

import java.util.HashMap;

/**
 * TODO
 */
public class Storage {
    
    private static HashMap<String, String> storage = new HashMap<String, String>();
    
    /**
     * TODO
     *
     * @return TODO
     */
    public static HashMap<String, String> getStorage() {
        
        return storage;
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param value TODO
     */
    public static void set(String key, String value) {
        
        storage.put(key, value);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public static String get(String key) {
        
        return storage.get(key);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     */
    public static void delete(String key) {
        
        storage.remove(key);
    }
}
