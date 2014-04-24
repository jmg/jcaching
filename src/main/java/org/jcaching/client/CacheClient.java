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
    
    /**
     * {@inheritDoc}
     * @see Object#CacheClient()
     */
    public CacheClient() {
    }
    
    /**
     * Constructor.
     *
     * @param port TODO
     */
    public CacheClient(int port) {
        
        this.port = port;
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public String get(String key) {
                        
        return sendMessage("GET", key, null);               
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param data TODO
     * @return TODO
     */
    public boolean set(String key, String data) {
        
        return sendMessage("SET", key, data).equals("ok");
    }

    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public boolean delete(String key) { 
        
        return sendMessage("DELETE", key, null).equals("ok");       
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
            
            writeMessage(action, key, data);
            response = readMessage();
            
            closeConnection();
            
        } catch (IOException e) { 
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
    private void writeMessage(String action, String key, String data) throws IOException {
                
        String message = Protocol.buildMessage(action, key, data);              
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
