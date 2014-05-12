/**
 * ImplementationClassLoadException
 *
 * The most general exception for framework JCaching.
 */

package org.jcaching.exception;

/**
 * Represents the most general kind of exception closely related to JCaching
 * framework.
 */
public class ImplementationClassLoadException extends Exception {

    /**
     * Automatically generated serial version Id.
     */
    private static final long serialVersionUID = -1501687611077572051L;

    /**
     * Default constructor.
     */
    public ImplementationClassLoadException() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see Exception#ImplementationClassLoadException(String)
     */
    public ImplementationClassLoadException(String message) {
        super(message);
    }
}
