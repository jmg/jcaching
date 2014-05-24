package org.jcaching.factory;

import java.lang.reflect.Constructor;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jcaching.exception.ImplementationClassLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFactory<T> {
	
	protected Class<T> type;
	protected Configuration configuration;
	private Logger logger;

	public BaseFactory(Class<T> t, Configuration configuration) {		
			
		this.type = t;
		this.configuration = configuration;		
		logger = LoggerFactory.getLogger(this.getClass());		
	}
	
	protected abstract String getConfigurationKey();
	
	protected T instantiate(Class<?> clazz) throws Exception {		
	 
		Constructor<?> constructor = clazz.getConstructor();
		return this.type.cast(constructor.newInstance());
	}

	public T getObjectInstance() throws ImplementationClassLoadException {			
		
		String configurationKey = this.getConfigurationKey();
	    String className =
	        configuration.getString(configurationKey, null);
	
	    if (className == null) {
	        throw new ImplementationClassLoadException(
	            String.format("Missing property key on configuration file: %s",
	                configurationKey)
	        );
	    }
	
	    logger.info("Creating instance for configured class {}",
	        className);
	
	    T instance = null;
	
	    try {
	        Class<?> clazz = ClassUtils.getClass(className);			
	        instance = instantiate(clazz);
	
	    } catch (Exception e) {
	        throw new ImplementationClassLoadException(
	            String.format("Exception trying to make the instance: %s",
	                ExceptionUtils.getStackTrace(e))
	        );
	    }            
	
	    logger.info("Instance created successful :)");
	    return instance;
	}	

}