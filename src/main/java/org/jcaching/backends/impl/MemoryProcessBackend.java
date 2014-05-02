/**
 * MemoryProcessBackend
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl;

import org.jcaching.backends.CacheBackend;
import org.jcaching.client.CacheClient;

/**
 * TODO description
 */
public class MemoryProcessBackend implements CacheBackend {
    
    CacheClient client = new CacheClient();

    /**
     * Default constructor.
     */
    public MemoryProcessBackend() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(String key, String serializedObj) {

        return client.set(key, serializedObj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(String key, String serializedObj, int timeout) {

        return client.set(key, serializedObj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(String key) {
        
        return client.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String key) {
        
        return client.delete(key);
    }
}
