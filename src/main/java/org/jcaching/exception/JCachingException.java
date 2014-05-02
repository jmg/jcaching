/**
 * JCachingException
 *
 * The most general exception for framework JCaching.
 */

package org.jcaching.exception;

/**
 * Represents the most general kind of exception closely related to JCaching
 * framework.
 */
public class JCachingException extends Exception {

    /**
     * Automatically generated serial version Id.
     */
    private static final long serialVersionUID = -1501687611077572051L;

    /**
     * Default constructor.
     */
    public JCachingException() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see Exception#JCachingException(String)
     */
    public JCachingException(String message) {
        super(message);
    }
}
