/**
 * CacheBackendFactory
 *
 * TODO Description if available.
 */

package org.jcaching.backends.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.CacheBackend;
import org.jcaching.factory.BaseFactory;

/**
 * TODO
 */
public class CacheBackendFactory<T extends CacheBackend> extends BaseFactory<T> {

    /**
     * TODO
     *
     * @param configuration TODO
     */
    public CacheBackendFactory(Configuration configuration) {
        super(configuration);
    }
    
	@Override
	protected T instantiate(Class<?> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
 		
		Constructor<?> constructor = clazz.getConstructor(Configuration.class);
	    return (T) constructor.newInstance(configuration);
	}
	
	@Override
	protected String getConfigurationKey() {
 
		return "jcaching.backend.className";
	}	
} 

// vim:ft=java ts=4 tw=80 cc=+1: 
