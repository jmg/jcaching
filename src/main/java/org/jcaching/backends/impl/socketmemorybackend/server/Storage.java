/**
 * Storage
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.server;

import java.util.HashMap;

import org.jcaching.backends.impl.socketmemorybackend.protocol.TimedObject;


/**
 * TODO
 */
public class Storage extends HashMap<String, TimedObject> {
        
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
    public void set(String key, TimedObject value) {
        
        super.put(key, value);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public String get(String key) {
        
    	TimedObject timedObject = super.get(key);
    	if (timedObject == null || timedObject.expired()) {
    		this.delete(key);
    		return null;
    	}
    	
        return timedObject.getValue();
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
