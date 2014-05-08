/**
 * ProtocolException
 *
 * TODO
 */

package org.jcaching.backends.socketmemorybackend.protocol.exception;

import org.jcaching.exception.JCachingException;


/**
 * TODO
 */
public class ProtocolException extends JCachingException {

    /**
     * Automatically generated serial version Id.
     */
    private static final long serialVersionUID = -946263576350938280L;

    /** 
     * Default constructor.
     */
    public ProtocolException() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see JCachingException#ProtocolException(String)
     */
    public ProtocolException(String message) {
        super(message);
    }
}
