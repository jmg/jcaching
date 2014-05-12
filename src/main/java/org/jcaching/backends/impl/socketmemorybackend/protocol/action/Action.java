/**
 * Action
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.action;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;


/**
 * TODO description
 */
public interface Action {
    
    /**
     * TODO
     *
     * @param message TODO
     * @return TODO
     */
    public String execute(Message message) throws InvalidActionException;
}
