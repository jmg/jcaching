/**
 * CacheDeamon
 *
 * TODO Description if available.
 */

package org.jcaching.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.jcaching.config.Config;
import org.jcaching.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO description
 */
public class CacheDeamon {
    
    private static Logger logger = LoggerFactory.getLogger(CacheDeamon.class);

    private static CacheDeamon instance;    

    private Integer port = null;
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
    	
    	Config config = Config.getInstance();
    	
		protocol = config.getProtocol();
		
		if (port == null) { 
			port = config.getPort();
		}
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
            logger.info("Cache server running on: {}", port.toString());
            
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
        logger.info("Connection received from {}", client.getInetAddress().getHostName());
        
        return client;
    }
}
