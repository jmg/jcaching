/**
 * SimpleProtocol
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol.impl;

import java.util.Arrays;
import java.util.List;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.Action;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl.Delete;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl.Get;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl.Set;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;

/**
 * TODO
 */
public class SimpleProtocol implements Protocol {
    
    /**
     * Default constructor.
     */
    public SimpleProtocol() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildMessage(String action, String key, String data) {
        
        String message = action + " " + key;
        if (data != null) {
            message += " " + data;
        }
        
        return message + "\n";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String buildResponse(Message message) throws InvalidActionException {
                        
        return message.execute(message); 
    }
    
    /**
     * {@inheritDoc}
     * @throws InvalidActionException 
     */
    @Override
    public Message parseMessage(String message) throws InvalidActionException {
        
        String[] parts = message.split("\\s+");
        
        String action = parts[0];
        String key = parts[1];        
        String value = (parts.length > 2) ? joinParts(parts) : null;
        
        return new Message(action, key, value, this);
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public String parseResponse(String response) {
        
        if (response.equals("<null>")) {
            return null;
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSetAction() {
        return "SET";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGetAction() {
        return "GET";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeleteAction() {
        return "DELETE";
    }

    /**
     * {@inheritDoc}
     * @throws InvalidActionException 
     */
    @Override
    public Action getAction(String action) throws InvalidActionException {
        
        if (action.equals(getGetAction())) {
            return new Get();
        } else if (action.equals(getSetAction())) {
            return new Set();
        } else if (action.equals(getDeleteAction())) {
            return new Delete();
        }
        
        throw new InvalidActionException();
    }

    /**
     * TODO
     *
     * @param parts TODO
     * @return TODO
     */
    private String joinParts(String[] parts) {
        
        StringBuffer buffer = new StringBuffer();
        String value;
        List<String> list = Arrays.asList(parts).subList(2, parts.length);
        for (String s: list) {
            buffer.append(s + " ");
        }       
        value = buffer.toString();
        value = value.substring(0, value.length() - 1);
        return value;
    }
}
