/**
 * Protocol
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol;

import org.jcaching.backends.impl.socketmemorybackend.protocol.action.Action;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;

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
    String buildClientMessage(String action, String key, String data, Integer timeout) throws InvalidActionException;

    /**
     * TODO
     *
     * @param message TODO
     * @return TODO
     * @throws InvalidActionException TODO
     */
    String buildServerResponse(Message message) throws InvalidActionException;

    /**
     * TODO
     *
     * @param message TODO
     * @return TODO
     */
    String parseServerResponse(String message);

    /**
     * TODO
     *
     * @param message TODO
     * @return TODO
     * @throws InvalidActionException 
     */
    Message parseClientMessage(String message) throws InvalidActionException;    
    
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
