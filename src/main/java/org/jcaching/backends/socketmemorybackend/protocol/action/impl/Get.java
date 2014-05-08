/**
 * Get
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
public class Get implements Action {

    /**
     * Default constructor.
     */
    public Get() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Message message) {
        
        String response = Storage.getInstance().get(message.getKey());
        if (response == null) {
            response = "<null>";
        }
        return response;
    }
}
