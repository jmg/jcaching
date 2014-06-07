/**
 * Delete
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
        
        MemoryMapStorage.getInstance().delete(message.getKey());
        return "ok";
    }
}
