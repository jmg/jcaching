/**
 * InvalidAction
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.exception;


/**
 * TODO
 */
public class InvalidActionException extends ProtocolException {

    /**
     * Automatically generated serial version Id.
     */
    private static final long serialVersionUID = -616427026985649172L;

    /**
     * Default constructor.
     */
    public InvalidActionException() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see ProtocolException#InvalidActionException(String)
     */
    public InvalidActionException(String message) {
        super(message);
    }
}
