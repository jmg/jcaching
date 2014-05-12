/**
 * Cache
 *
 * TODO Description if available.
 */

package org.jcaching.cache;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.factory.CacheBackendFactory;
import org.jcaching.exception.ImplementationClassLoadException;

/**
 * Facade to interact with the cache user
 */
public class Cache {

    private CacheBackend backend;

    /**
     * TODO
     *
     * @param configuration TODO
     * @throws ImplementationClassLoadException The indicated backend
     * implementation class cannot be loaded or instantiated.
     */
    public Cache(Configuration configuration)
            throws ImplementationClassLoadException {
        CacheBackendFactory backendFactory =
            new CacheBackendFactory(configuration);

        backend = backendFactory.getCacheBackendInstance();
    }

    /**
     * TODO
     *
     * @param backend TODO
     */
    public Cache(CacheBackend backend) {
        this.backend = backend;
    }

    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public Object get(String key) {
        return backend.get(key);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param value TODO
     */
    public void set(String key, String value) {
        backend.set(key, value);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param value TODO
     * @param timeout TODO
     */
    public void set(String key, String value, int timeout) {
        backend.set(key, value, timeout);
    }

    /**
     * TODO
     *
     * @param key TODO
     */
    public void delete(String key) {
        backend.delete(key);
    }
}
