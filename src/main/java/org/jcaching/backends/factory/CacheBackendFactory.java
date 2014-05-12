/**
 * CacheBackendFactory
 *
 * TODO Description if available.
 */

package org.jcaching.backends.factory;

import java.lang.reflect.Constructor;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jcaching.backends.CacheBackend;
import org.jcaching.exception.ImplementationClassLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 */
public class CacheBackendFactory {

    /**
     * The key on properties configuration file holding the backend full
     * quallified class name to instantiate.
     */
    public static final String CONFIGURATION_BACKEND_CLASSNAME_KEY =
        "jcaching.backend.className";

    private static final Logger logger =
        LoggerFactory.getLogger(CacheBackendFactory.class);

    private Configuration configuration;

    /**
     * TODO
     *
     * @param configuration TODO
     */
    public CacheBackendFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public CacheBackend getCacheBackendInstance()
        throws ImplementationClassLoadException {

        String className =
            configuration.getString(CONFIGURATION_BACKEND_CLASSNAME_KEY, null);

        if (className == null) {
            throw new ImplementationClassLoadException(
                String.format("Missing property key on configuration file: %s",
                    CONFIGURATION_BACKEND_CLASSNAME_KEY)
            );
        }

        logger.info("Creating backend instance for configured class {}",
            className);

        CacheBackend backendInstance = null;

        try {
            Class<?> clazz = ClassUtils.getClass(className);

            Constructor<?> constructor =
                clazz.getConstructor(Configuration.class);

            backendInstance =
                (CacheBackend) constructor.newInstance(configuration);

        } catch (Exception e) {
            throw new ImplementationClassLoadException(
                String.format("Exception trying to instantiate the cache " +
                    "backend implementation: %s",
                    ExceptionUtils.getStackTrace(e))
            );
        }            

        logger.info("Backend instance created successful :)");
        return backendInstance;
    }
} 

// vim:ft=java ts=4 tw=80 cc=+1: 
