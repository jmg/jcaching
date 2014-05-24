/**
 * Set
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.TimedObject;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.Action;
import org.jcaching.backends.impl.socketmemorybackend.server.Storage;

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
        Storage.getInstance().set(message.getKey(), timedObject);
        return "ok";
    }

}
