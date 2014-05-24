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
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;

/**
 * TODO
 */
public class SimpleProtocol extends BaseProtocol implements Protocol {
    
    /**
     * Default constructor.
     */
    public SimpleProtocol() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildClientMessage(String action, String key, String data, Integer timeout) {
        
        String message = action + " " + key;
        if (data != null) {
            message += " " + data;
        }
        
        return message + "\n";
    }
    
    /**
     * {@inheritDoc}
     * @throws InvalidActionException 
     */
    @Override
    public Message parseClientMessage(String message) throws InvalidActionException {
        
        String[] parts = message.split("\\s+");
        
        String action = parts[0];
        String key = parts[1];        
        String value = (parts.length > 2) ? joinParts(parts) : null;
        
        return new Message(action, key, value, null, this);
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
