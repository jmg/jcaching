/**
 * Protocol
 *
 * TODO Description if available.
 */

package org.jcaching.protocol;

/**
 * TODO description
 */
public interface Protocol {
	
	public String buildMessage(String action, String key, String data);
    public String buildResponse(Message message);
    public String parseResponse(String message);
    public Message parseMessage(String message);    
    
	public String getSetAction();
	public String getGetAction();		
	public String getDeleteAction();
	public Action getAction(String action);	
}
