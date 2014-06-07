/**
 * Get
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.Action;
import org.jcaching.storage.impl.memorystorage.MemoryMapStorage;

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
        
        String response = MemoryMapStorage.getInstance().get(message.getKey());
        if (response == null) {
            response = "<null>";
        }
        return response;
    }
}
