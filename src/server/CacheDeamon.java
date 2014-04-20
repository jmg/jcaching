package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import protocol.Message;
import protocol.Protocol;

import utils.ServerLogger;

public class CacheDeamon {
	
	Integer defaultPort = 22122;
	Integer port;
	ServerSocket server;
	
	Socket client;
	DataOutputStream os;
	BufferedReader is;
		
	static CacheDeamon instance;	
	private boolean running = false;
	
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
		
		ServerLogger.log("Connection received from " + client.getInetAddress().getHostName());
		
		is = new BufferedReader(new InputStreamReader(client.getInputStream()));
		os = new DataOutputStream(client.getOutputStream());
	}
	
	private String readMessage() throws IOException {			
		
	    String response = is.readLine();
	    ServerLogger.log("Read: " + response);
	    
	    return response;
	}
	
	private void closeConnection() throws IOException {
		
		os.close();
        is.close();
        client.close();
	}
	
	void writeMessage(String message) throws IOException {
		
        os.writeBytes(message);        
        ServerLogger.log("Wrote: " + message);                
    }
}
