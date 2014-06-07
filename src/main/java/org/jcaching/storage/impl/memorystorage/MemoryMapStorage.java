/**
 * Storage
 *
 * TODO Description if available.
 */

package org.jcaching.storage.impl.memorystorage;

import java.util.HashMap;

import org.jcaching.storage.StorageBackend;
import org.jcaching.storage.StorageObject;



/**
 * TODO
 */
public class MemoryMapStorage extends HashMap<String, StorageObject> implements StorageBackend {
        
    private static final long serialVersionUID = -7691679274906679651L;    
    private static MemoryMapStorage instance = null;
    
    public static MemoryMapStorage getInstance() {
        
        if (instance == null) {
            instance = new MemoryMapStorage();
        }
        
        return instance;
    }
        
    /**
     * TODO
     *
     * @param key TODO
     * @param value TODO
     */
    @Override
    public void set(String key, StorageObject value) {
        
        super.put(key, value);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    @Override
    public String get(String key) {
        
    	StorageObject storageObject = super.get(key);
    	if (storageObject == null) {
    		return null;
    	}    	
    	else if (storageObject.expired()) {
    		this.delete(key);
    		return null;
    	}
    	
        return storageObject.getValue();
    }

    /**
     * TODO
     *
     * @param key TODO
     */
    @Override
    public void delete(String key) {
        
        super.remove(key);
    }

}
