package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import utils.Logger;
import utils.ServerLogger;

public class CacheDeamon {
	
	Integer defaultPort = 22122;
	Integer port;
	ServerSocket server;
	
	Socket client;
	DataOutputStream os;
	BufferedReader is;
		
	static CacheDeamon instance;
	Logger logger = ServerLogger.getInstance();
	
	private CacheDeamon() {
						
		this.port = defaultPort;
	}
	
	public static CacheDeamon getInstance() {
		
		if (instance == null) {
			instance = new CacheDeamon();
		}
		
		return instance;
	}
	
	public CacheDeamon(Integer port) {
		
		this.port = port;			   
	}
	
	public void serverForever() {
			
		try {
			server = new ServerSocket(port);
			logger.log("Cache server running on: " + port.toString());
			
		} catch (IOException e1) {
 
			e1.printStackTrace();
		}
		
		while(true) {
		
			try {
			
				acceptConnections();
				
				String message = readMessage();
				writeMessage("response");
				
				closeConnection();	
			
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
	}
	
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

	private void acceptConnections() throws IOException {
		
		client = server.accept();
		
		logger.log("Connection received from " + client.getInetAddress().getHostName());
		
		is = new BufferedReader(new InputStreamReader(client.getInputStream()));
		os = new DataOutputStream(client.getOutputStream());
	}
	
	private String readMessage() throws IOException {			
		
	    String response = is.readLine();
	    logger.log("Read: " + response);
	    
	    return response;
	}
	
	private void closeConnection() throws IOException {
		
		os.close();
        is.close();
        client.close();
	}
	
	void writeMessage(String message) throws IOException {
		
        os.writeBytes(message);        
        logger.log("Wrote: " + message);                
    }
}
