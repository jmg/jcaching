/**
 * Message
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.protocol;

import org.jcaching.backends.impl.socketmemorybackend.protocol.action.Action;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;

/**
 * TODO
 */
public class Message {
        
    private Action action;
    private String key;
    private String value;
	private Integer timeout;       
    
    /**
     * Constructor.
     *
     * @param action TODO
     * @param key TODO
     * @param value TODO
     * @throws InvalidActionException 
     */
    public Message(String action, String key, String value, Integer timeout, Protocol protocol) throws InvalidActionException {
        super();
                
        this.key = key;
        this.value = value;        
        this.timeout = timeout;
        this.action = this.setAction(action, protocol);        
    }    

    /**
     * TODO
     *
     * @return TODO
     */
    public String getKey() {
        return key;
    }

    /**
     * TODO
     *
     * @param key TODO
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public String getValue() {
        return value;
    }

    /**
     * TODO
     *
     * @param value TODO
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Action getAction() {
        return action;
    }
    
    /**
     * TODO
     *
     * @param value TODO
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * TODO
     *
     * @param action TODO
     * @param protocol 
     * @return TODO
     * @throws InvalidActionException 
     */
    public Action setAction(String action, Protocol protocol) throws InvalidActionException {
        
        return protocol.getAction(action);
    }

    public String execute() throws InvalidActionException {
        
        return getAction().execute(this);
    }
    
}
