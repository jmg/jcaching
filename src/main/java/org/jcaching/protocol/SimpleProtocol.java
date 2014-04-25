package org.jcaching.protocol;

import java.util.Arrays;
import java.util.List;

public class SimpleProtocol implements Protocol {
	
	@Override
    public String buildMessage(String action, String key, String data) {
        
        String message = action + " " + key;
        if (data != null) {
            message += " " + data;
        }
        
        return message + "\n";
    }
    
	@Override
    public String buildResponse(Message message) {
                        
        return message.execute(message); 
    }
    
	@Override
    public Message parseMessage(String message) {
        
        String[] parts = message.split("\\s+");
        
        String action = parts[0];
        String key = parts[1];        
        String value = (parts.length > 2) ? joinParts(parts) : null;
        
        return new Message(action, key, value, this);
    }

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
	
	@Override
	public String parseResponse(String response) {
		
		if (response.equals("<null>")) {
			return null;
		}
		return response;
	}

	@Override
	public String getSetAction() {
		return "SET";
	}

	@Override
	public String getGetAction() {
		return "GET";
	}

	@Override
	public String getDeleteAction() {
		return "DELETE";
	}

	@Override
	public Action getAction(String action) {
		
		if (action.equals(getGetAction())) {
            return new Get();
        } else if (action.equals(getSetAction())) {
            return new Set();
        } else if (action.equals(getDeleteAction())) {
            return new Delete();
        }
		
		throw new RuntimeException("No valid action for this protocol");
	}

}
