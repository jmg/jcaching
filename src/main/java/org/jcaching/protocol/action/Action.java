/**
 * Action
 *
 * TODO Description if available.
 */

package org.jcaching.protocol.action;

import org.jcaching.protocol.Message;
import org.jcaching.protocol.exception.InvalidActionException;


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
