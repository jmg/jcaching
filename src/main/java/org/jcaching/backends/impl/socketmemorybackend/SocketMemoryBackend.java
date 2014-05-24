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
import org.jcaching.serializer.Serializer;
import org.jcaching.serializer.factory.SerializerFactory;

/**
 * TODO description
 */
public class SocketMemoryBackend extends BaseCacheBackend implements
        CacheBackend {

    private CacheClient client;
	private Serializer serializer;

    /**
     * Default constructor.
     * @throws ImplementationClassLoadException
     */
    public SocketMemoryBackend(Configuration configuration)
            throws ImplementationClassLoadException {
        super(configuration);
        client = new CacheClient(configuration);
        
        SerializerFactory<Serializer> factory = new SerializerFactory<Serializer>(Serializer.class, configuration);
        serializer = factory.getObjectInstance();
    }        

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String key, Object object) {
    	
        client.set(key, serializer.serialize(object));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String key, Object object, int timeout) {
    	       	
        client.set(key, serializer.serialize(object), timeout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key) {
        
        return serializer.deserialize(client.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String key) {
        
        client.delete(key);
    }
}
