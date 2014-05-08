/**
 * Storage
 *
 * TODO Description if available.
 */

package org.jcaching.backends.socketmemorybackend.server;

import java.util.HashMap;


/**
 * TODO
 */
public class Storage extends HashMap<String, String> {
        
	private static final long serialVersionUID = -7691679274906679651L;	   
    private static Storage instance = null;
    
    public static Storage getInstance() {
		
    	if (instance == null) {
    		instance = new Storage();
    	}
    	
    	return instance;
	}
        
    /**
     * TODO
     *
     * @param key TODO
     * @param value TODO
     */
    public void set(String key, String value) {
        
        super.put(key, value);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public String get(String key) {
        	
        return super.get(key);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     */
    public void delete(String key) {
        
        super.remove(key);
    }

}
