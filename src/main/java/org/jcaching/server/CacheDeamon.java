/**
 * CacheDeamon
 *
 * TODO Description if available.
 */

package org.jcaching.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.jcaching.protocol.Message;
import org.jcaching.protocol.Protocol;
import org.jcaching.utils.ServerLogger;

/**
 * TODO description
 */
public class CacheDeamon {
    
    private static CacheDeamon instance;    

    private Integer port = 22122;
    private ServerSocket server;

    private Socket client;
    private DataOutputStream os;
    private BufferedReader is;

    private boolean running = false;
    
    /**
     * Private default constructor.
     */
    private CacheDeamon() {
    }
    
    /**
     * Private constructor.
     *
     * @param port TODO
     */
    public CacheDeamon(Integer port) {
        
        this.port = port;              
    }

     /**
     * TODO
     *
     * @return TODO
     */
    public static CacheDeamon getInstance() {
        
        if (instance == null) {
            instance = new CacheDeamon();
        }
        
        return instance;
    }
    
    /**
     * TODO
     */
    public void serverForever() {
                
        if (running) {
            return;
        }
        running = true;
            
        try {
            server = new ServerSocket(port);
            ServerLogger.log("Cache server running on: " + port.toString());
            
        } catch (IOException e) {
 
            e.printStackTrace();
        }
        
        while(true) {
        
            try {
            
                acceptConnections();
                                
                Message message = Protocol.parseMessage(readMessage());         
                writeMessage(Protocol.buildResponse(message));
                
                closeConnection();  
            
            } catch (IOException e) {           
                e.printStackTrace();
            }
        }
    }
    
    /**
     * TODO
     */
    public static void serverForeverInAThread() {
        
        try { 
            (new Thread(new Runnable(){ 
                public void run() { 
                    
                    CacheDeamon deamon = CacheDeamon.getInstance();                 
                    deamon.serverForever();
                }; 
            })).start(); 
            
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }

    /**
     * TODO
     *
     * @throws IOException TODO
     */
    private void acceptConnections() throws IOException {
        
        client = server.accept();
        
        ServerLogger.log("Connection received from " + client.getInetAddress().getHostName());
        
        is = new BufferedReader(new InputStreamReader(client.getInputStream()));
        os = new DataOutputStream(client.getOutputStream());
    }
    
    /**
     * TODO
     *
     * @return TODO
     *
     * @throws IOExceptiona TODO
     */
    private String readMessage() throws IOException {           
        
        String response = is.readLine();
        ServerLogger.log("Read: " + response);
        
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
     * @param message TODO
     *
     * @throws IOException TODO
     */
    void writeMessage(String message) throws IOException {
        
        os.writeBytes(message);        
        ServerLogger.log("Wrote: " + message);                
    }
}
