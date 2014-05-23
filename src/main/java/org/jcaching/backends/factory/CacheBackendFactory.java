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
import org.jcaching.exception.ImplementationClassLoadException;
import org.jcaching.factory.BaseFactory;

/**
 * TODO
 */
public class CacheBackendFactory extends BaseFactory {

    /**
     * TODO
     *
     * @param configuration TODO
     */
    public CacheBackendFactory(Configuration configuration) {
        super(configuration);
    }
    
	public CacheBackend getCacheBackendInstance() throws ImplementationClassLoadException {
		
		return (CacheBackend) this.getObjectInstance("jcaching.backend.className");	    	            
	}
	
	@Override
	protected Object getInstance(Class<?> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
 		
		Constructor<?> constructor = clazz.getConstructor(Configuration.class);
	    return constructor.newInstance(configuration);
	}
} 

// vim:ft=java ts=4 tw=80 cc=+1: 
