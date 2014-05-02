/**
 * Protocol
 *
 * TODO Description if available.
 */

package org.jcaching.protocol;

import org.jcaching.protocol.action.Action;
import org.jcaching.protocol.exception.InvalidActionException;

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
     */
    Message parseMessage(String message);    
    
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
     */
    Action getAction(String action);    
}
