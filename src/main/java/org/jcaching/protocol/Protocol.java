/**
 * Protocol
 *
 * TODO Description if available.
 */

package org.jcaching.protocol;

/**
 * TODO description
 */
public class Protocol {
    
    public static String buildMessage(String action, String key, String data) {
        
        String message = action + " " + key;
        if (data != null) {
            message += " " + data;
        }
        
        return message + "\n";
    }
    
    public static String buildResponse(Message message) {
                
        Action action = message.getAction();        
        return action.execute(message); 
    }
    
    public static Message parseMessage(String message) {
        
        String[] parts = message.split("\\s+");
        
        String action = parts[0];
        String key = parts[1];      
        String value = (parts.length > 2) ? parts[2] : null;
        
        return new Message(action, key, value);
    }
}
