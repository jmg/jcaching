/**
 * CacheDeamon
 *
 * TODO Description if available.
 */

package org.jcaching.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.jcaching.protocol.Protocol;
import org.jcaching.settings.MemoryProcessSettings;
import org.jcaching.utils.ServerLogger;

/**
 * TODO description
 */
public class CacheDeamon {
    
    private static CacheDeamon instance;    

    private Integer port = 22122;
    private ServerSocket server;       

    private boolean running = false;
    private Protocol protocol = null;
    
    /**
     * Private default constructor.
     */
    private CacheDeamon() {
    	    	
    	initialize();
    }
    
    /**
     * Private constructor.
     *
     * @param port TODO
     */
    public CacheDeamon(Integer port) {
        
        this.port = port;
        initialize();
    }
    
    /**
     * Private constructor.
     *
     * @param port TODO
     */
    private void initialize() {
		protocol = MemoryProcessSettings.getProtocol();		
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
            
                Socket client = acceptConnections();
                new ClientConnection(client, protocol).start();
            
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
     * @return 
     *
     * @throws IOException TODO
     */
    private Socket acceptConnections() throws IOException {
        
        Socket client = server.accept();        
        ServerLogger.log("Connection received from " + client.getInetAddress().getHostName());
        
        return client;
    }
}
