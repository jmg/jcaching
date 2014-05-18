/**
 * JCaching
 *
 * TODO Description if available.
 */

package org.jcaching;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jcaching.cache.Cache;
import org.jcaching.exception.ImplementationClassLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 */
public class JCaching {
 
    /**
     * The properties configuration file name on classpath.
     */
    public static final String CONFIGURATION_FILE_PROPERTIES =
        "jcaching.properties";

    private static final Logger logger =
        LoggerFactory.getLogger(JCaching.class);

    private static Cache instance;

    private static Configuration configuration;

    /**
     * Private constructor to avoid instantiation.
     */
    private JCaching() {
    }

    /**
     * TODO
     * @throws ImplementationClassLoadException TODO
     * @throws ConfigurationException TODO
     */
    public static Cache getCache() throws ConfigurationException,
            ImplementationClassLoadException {
        return getCache(false);
    }

    /**
     * TODO
     * @throws ImplementationClassLoadException TODO
     * @throws ConfigurationException TODO
     */
    public static Cache getCache(boolean forceReload)
            throws ConfigurationException, ImplementationClassLoadException {
        if (instance == null || forceReload) {
            logger.debug("Forcibly creating a new cache instance... ");
            instance = new Cache(getConfiguration(forceReload));
            logger.debug("Done :)");
        }

        return instance;
    }

    /**
     * TODO
     * @return TODO
     * @throws ConfigurationException TODO
     */
    public static Configuration getConfiguration()
            throws ConfigurationException {

        return getConfiguration(false);
    }

    /**
     * @param configuration Sets the configuration instance to use.
     */
    public static void setConfiguration(Configuration configuration) {
        JCaching.configuration = configuration;
    }

    /**
     * TODO
     *
     * @param forceReload TODO
     * @return TODO
     * @throws ConfigurationException The properties file indicated cannot be
     * found on classpath.
     */
    public static Configuration getConfiguration(boolean forceReload)
            throws ConfigurationException {

        if (configuration == null || forceReload) {
            logger.debug("Forcibly loading configuration parameters... "); 

            configuration =
                new PropertiesConfiguration(CONFIGURATION_FILE_PROPERTIES);
            
            logger.debug("Done :)"); 
        }

        return configuration;
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
