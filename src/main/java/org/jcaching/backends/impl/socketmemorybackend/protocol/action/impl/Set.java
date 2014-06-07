/**
 * Set
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.Action;
import org.jcaching.storage.impl.memorystorage.MemoryMapStorage;
import org.jcaching.storage.impl.memorystorage.TimedObject;

/**
 * TODO description
 */
public class Set implements Action {

    /**
     * Default constructor.
     */
    public Set() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Message message) {
    	    	
    	TimedObject timedObject = new TimedObject(message.getValue(), message.getTimeout()); 
        MemoryMapStorage.getInstance().set(message.getKey(), timedObject);
        return "ok";
    }

}
