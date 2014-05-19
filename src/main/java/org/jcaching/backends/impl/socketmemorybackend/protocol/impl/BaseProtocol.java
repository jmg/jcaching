package org.jcaching.backends.impl.socketmemorybackend.protocol.impl;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.Action;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl.Delete;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl.Get;
import org.jcaching.backends.impl.socketmemorybackend.protocol.action.impl.Set;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;

public abstract class BaseProtocol implements Protocol {

	public BaseProtocol() {
		super();
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
     * {@inheritDoc}
     * @throws InvalidActionException 
     */
    @Override
    public String buildResponse(Message message) throws InvalidActionException {

    	return message.execute(message);
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
}