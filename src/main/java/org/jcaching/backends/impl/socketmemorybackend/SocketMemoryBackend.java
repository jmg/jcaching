/**
 * MemoryProcessBackend
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.impl.BaseCacheBackend;
import org.jcaching.backends.impl.socketmemorybackend.client.CacheClient;
import org.jcaching.exception.ImplementationClassLoadException;

import com.google.gson.Gson;

/**
 * TODO description
 */
public class SocketMemoryBackend extends BaseCacheBackend implements
        CacheBackend {

    private CacheClient client;

    /**
     * Default constructor.
     * @throws ImplementationClassLoadException
     */
    public SocketMemoryBackend(Configuration configuration)
            throws ImplementationClassLoadException {
        super(configuration);
        client = new CacheClient(configuration);
    }
    
    private String serialize(Object object) {
    	    	
    	return new Gson().toJson(object);
    }
    
    private Object deserialize(String string, Class<?> klass) {
    	
    	return new Gson().fromJson(string, klass);    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String key, Object object) {
    	
        client.set(key, serialize(object));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String key, Object object, int timeout) {

        client.set(key, serialize(object));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key, Class<?> klass) {
        
        return deserialize(client.get(key), klass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String key) {
        
        client.delete(key);
    }
}
