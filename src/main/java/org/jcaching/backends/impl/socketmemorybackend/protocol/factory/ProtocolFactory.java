/**
 * ProtocolFactory
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.factory;

import java.lang.reflect.Constructor;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.exception.ImplementationClassLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 */
public class ProtocolFactory {

    private static final String CONFIGURATION_PREFIX =
        "jcaching.backends.socketmemorybackend";

    /**
     * The key on properties configuration file holding the protocol class name
     * to instantiate.
     */
    public static final String CONFIGURATION_PROTOCOL_NAME_KEY =
        CONFIGURATION_PREFIX + ".protocol";

    private static final Logger logger =
        LoggerFactory.getLogger(ProtocolFactory.class);

    private Configuration configuration;

    /**
     * TODO
     *
     * @param configuration TODO
     */
    public ProtocolFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public Protocol getProtocolInstance()
        throws ImplementationClassLoadException {

        String className =
            configuration.getString(CONFIGURATION_PROTOCOL_NAME_KEY, null);

        if (className == null) {
            throw new ImplementationClassLoadException(
                String.format("Missing property key on configuration file: %s",
                    CONFIGURATION_PROTOCOL_NAME_KEY)
            );
        }

        logger.info("Creating protocol instance for configured class {}",
            className);

        Protocol instance = null;

        try {
            Class<?> clazz = ClassUtils.getClass(className);

            Constructor<?> constructor = clazz.getConstructor();

            instance = (Protocol) constructor.newInstance();

        } catch (Exception e) {
            throw new ImplementationClassLoadException(
                String.format("Exception trying to instantiate the protocol " +
                    "implementation: %s",
                    ExceptionUtils.getStackTrace(e))
            );
        }            

        logger.info("Protocol instance created successful :)");
        return instance;
    }
} 

// vim:ft=java ts=4 tw=80 cc=+1: 
