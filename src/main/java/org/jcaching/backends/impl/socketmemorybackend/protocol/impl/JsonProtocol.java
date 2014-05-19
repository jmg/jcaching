/**
 * JsonProtocol
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.impl;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;

import com.google.gson.Gson;

/**
 * TODO
 */
public class JsonProtocol extends BaseProtocol implements Protocol {
    
    /**
     * Default constructor.
     */
    public JsonProtocol() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildMessage(String action, String key, String data) {
    	
    	GsonMessage gsonMessage = new GsonMessage(action, key, data);
    	return new Gson().toJson(gsonMessage) + "\n";
    }

    /**
     * {@inheritDoc}
     * @throws InvalidActionException 
     */
    @Override
    public Message parseMessage(String message) throws InvalidActionException {
        
    	GsonMessage gsonMessage = new Gson().fromJson(message, GsonMessage.class);
    	return new Message(gsonMessage.getAction(), gsonMessage.getKey(), gsonMessage.getData(), this);
    }
}
