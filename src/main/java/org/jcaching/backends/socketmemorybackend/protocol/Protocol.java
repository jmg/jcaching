/**
 * Protocol
 *
 * TODO Description if available.
 */

package org.jcaching.backends.socketmemorybackend.protocol;

import org.jcaching.backends.socketmemorybackend.protocol.action.Action;
import org.jcaching.backends.socketmemorybackend.protocol.exception.InvalidActionException;

/**
 * TODO description
 */
public interface Protocol {
    
    /**
     * TODO
     *
     * @param action TODO
     * @param key TODO
     * @param data TODO
     * @return TODO
     * @throws InvalidActionException TODO
     */
    String buildMessage(String action, String key, String data) throws InvalidActionException;

    /**
     * TODO
     *
     * @param message TODO
     * @return TODO
     * @throws InvalidActionException TODO
     */
    String buildResponse(Message message) throws InvalidActionException;

    /**
     * TODO
     *
     * @param message TODO
     * @return TODO
     */
    String parseResponse(String message);

    /**
     * TODO
     *
     * @param message TODO
     * @return TODO
     * @throws InvalidActionException 
     */
    Message parseMessage(String message) throws InvalidActionException;    
    
    /**
     * TODO
     *
     * @return TODO
     */
    String getSetAction();

    /**
     * TODO
     *
     * @return TODO
     */
    String getGetAction();      

    /** 
     * TODO
     *
     * @return TODO
     */
    String getDeleteAction();

    /**
     * TODO
     *
     * @param action TODO
     * @return TODO
     * @throws InvalidActionException 
     */
    Action getAction(String action) throws InvalidActionException;    
}
