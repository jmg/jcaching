/**
 * BaseCacheBackend
 *
 * Basic implementation for futures cache backend definitions.
 */

package org.jcaching.backends.impl;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.CacheBackend;

/**
 * Abstract base class for future backend implementations. It defines the
 * default constructor format to follow for a common interface.
 * @version
 */
public abstract class BaseCacheBackend implements CacheBackend {

    /**
     * The property configuration key storing the value for default timeout in
     * configuration file.
     */
    public static final String CONFIGURATION_DEFAULT_TIMEOUT_KEY =
        "jcaching.defaultTimeout";

    /**
     * The default timeout value if key is not present or not defined on
     * configuration fil.e
     */
    public static final int CONFIGURATION_DEFAULT_TIMEOUT_VALUE = 0;

    protected Configuration configuration;

    protected int defaultTimeout;

    /**
     * Absctract constructor.
     *
     * @param configuration The configuration object containing the parametrized
     * values for framework/backends.
     */
    public BaseCacheBackend(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        defaultTimeout = configuration.getInt(CONFIGURATION_DEFAULT_TIMEOUT_KEY,
            CONFIGURATION_DEFAULT_TIMEOUT_VALUE);
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
