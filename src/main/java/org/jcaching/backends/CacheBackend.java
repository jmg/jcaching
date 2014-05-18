/**
 * CacheBackend
 *
 * TODO Description if available.
 */

package org.jcaching.backends;

import org.apache.commons.configuration.Configuration;


/**
 * TODO description
 * @version
 */
public interface CacheBackend {
        
    /**
     * Set a object in cache using a key.
     *
     * @param key TODO
     * @param serializedObj TODO
     * @return TODO
     */
    void set(String key, Object value);

    /**
     * Set a object in cache using a key.
     *
     * @param key TODO
     * @param serializedObj TODO
     * @param timeout TODO
     * @return TODO
     */
    void set(String key, Object value, int timeout);
    
    /**
     * Returns the object from cache.
     *
     * @param key TODO
     * @return TODO
     */
    Object get(String key);
    
    /**
     * Delete a object from cache using a key.
     *
     * @param key TODO
     * @return TODO
     */
    void delete(String key);

    /**
     * @return Returns the configuration instance used to paremetrize the
     * framework/backend.
     */
    Configuration getConfiguration();

    /**
     * Sets all backend components to its initial values.
     */
    void initialize();	
}
