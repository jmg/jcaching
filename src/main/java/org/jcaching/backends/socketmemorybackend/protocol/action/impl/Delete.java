/**
 * Delete
 *
 * TODO Description if available.
 */

package org.jcaching.backends.socketmemorybackend.protocol.action.impl;

import org.jcaching.backends.socketmemorybackend.protocol.action.Action;
import org.jcaching.backends.socketmemorybackend.protocol.Message;
import org.jcaching.backends.socketmemorybackend.server.Storage;

/**
 * TODO description
 */
public class Delete implements Action {

    /**
     * Default constructor.
     */
    public Delete() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Message message) {
        
        Storage.getInstance().delete(message.getKey());
        return "ok";
    }
}
