/**
 * CacheClient
 *
 * TODO Description if available.
 */

package org.jcaching.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.jcaching.protocol.Protocol;
import org.jcaching.protocol.exception.InvalidActionException;
import org.jcaching.settings.MemoryProcessSettings;
import org.jcaching.utils.ClientLogger;

/**
 * TODO description
 */
public class CacheClient {
    
    String host = "localhost";
    Integer port = 22122;
    
    Socket client;
    
    DataOutputStream os;
    BufferedReader is;
    Protocol protocol;
    
    /**
     * {@inheritDoc}
     * @see Object#CacheClient()
     */
    public CacheClient() {
    	
    	initialize();
    }
    
    /**
     * Constructor.
     *
     * @param port TODO
     */
    public CacheClient(int port) {
        
        this.port = port;
        initialize();
    }
    
    /**
     * Private constructor.
     *
     * @param port TODO
     */
    public void initialize() {
    	
    	protocol = MemoryProcessSettings.getProtocol();
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
        
        ClientLogger.log("Wrote: " + message);
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
        ClientLogger.log("Read: " + response);
        
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
}
