/**
 * CacheClient
 *
 * TODO Description if available.
 */

package org.jcaching.backends.socketmemorybackend.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.jcaching.backends.socketmemorybackend.protocol.exception.InvalidActionException;
import org.jcaching.backends.socketmemorybackend.protocol.Protocol;
import org.jcaching.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO description
 */
public class CacheClient {

    private static Logger logger = LoggerFactory.getLogger(CacheClient.class);
    
    String host = null;
    Integer port = null;
    
    Socket client;
    
    DataOutputStream os;
    BufferedReader is;
    Protocol protocol;
    Config config;
    
    /**
     * {@inheritDoc}
     * @see Object#CacheClient()
     */
    public CacheClient(Config config) {
    	
    	this.config = config;
    	initialize();
    }
    
    /**
     * Private constructor.
     *
     * @param port TODO
     */
    public void initialize() {
    	
    	protocol = config.getProtocol();    	    	
    	port = config.getPort();    	
    	host = config.getHost();    	
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public String get(String key) {
                        
        return sendMessage(protocol.getGetAction(), key, null);               
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param data TODO
     * @return TODO
     */
    public boolean set(String key, String data) {
        
        return sendMessage(protocol.getSetAction(), key, data).equals("ok");
    }

    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public boolean delete(String key) { 
        
        return sendMessage(protocol.getDeleteAction(), key, null).equals("ok");       
    }

    /**
     * TODO
     *
     * @param action TODO
     * @param key TODO
     * @param data TODO
     * @return TODO
     */
    private String sendMessage(String action, String key, String data) {
        
        String response = null;
        try {
            
            getConnection();
            
            writeMessage(protocol.buildMessage(action, key, data));
            response = protocol.parseResponse(readMessage());
            
            closeConnection();
            
        } catch (IOException e) { 
            e.printStackTrace();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
 
        return response;
    }

    /**
     * TODO
     *
     * @throws IOException TODO
     */
    private void closeConnection() throws IOException {
        
        os.close();
        is.close();        
        client.close();
    }

    /**
     * TODO
     *
     * @param action TODO
     * @param key TODO
     * @param data TODO
     *
     * @throws IOException TODO
     */
    private void writeMessage(String message) throws IOException {
                               
        os.writeBytes(message);
        
        logger.debug("Wrote: {}", message);
    }
    
    /**
     * TODO
     *
     * @return TODO
     *
     * @throws IOException TODO
     */
    private String readMessage() throws IOException {       
        
        String response = is.readLine();
        logger.debug("Read: {}", response);
        
        return response;
    }

    /**
     * TODO
     *
     * @throws UnknownHostException TODO
     * @throws IOException TODO
     */
    private void getConnection() throws UnknownHostException, IOException {
        
        client = new Socket(host, port);
                
        os = new DataOutputStream(client.getOutputStream());
        is = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

	public void setConfig(Config config) {
		
		this.config = config;
	}   
}
