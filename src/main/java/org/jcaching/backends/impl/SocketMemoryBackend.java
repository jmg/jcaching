/**
 * MemoryProcessBackend
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl;

import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.socketmemorybackend.client.CacheClient;
import org.jcaching.config.Config;

/**
 * TODO description
 */
public class SocketMemoryBackend implements CacheBackend {
    
	CacheClient client;

    /**
     * Default constructor.
     */
    public SocketMemoryBackend() {
    	    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String key, Object object) {

        client.set(key, object.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String key, Object object, int timeout) {

        client.set(key, object.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key) {
        
        return client.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String key) {
        
        client.delete(key);
    }

	@Override
	public void setConfig(Config config) {
				
		client = new CacheClient(config);
	}
}
