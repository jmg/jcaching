package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.ClientLogger;
import utils.Logger;

public class CacheClient {
	
	Integer defaultPort = 22122;
	String host = "localhost";
	Integer port;
	
	Socket client;
	
	DataOutputStream os;
	BufferedReader is;
	
	Logger logger = ClientLogger.getInstance();
	
	public CacheClient() {
		
		this.port = defaultPort;
	}
	
	public CacheClient(int port) {
		
		this.port = port;
	}
	
	public String get(String key) {
		                
		return sendMessage("GET", key);      			
	}
	
	public boolean set(String key, String data) {
		
		return sendMessage("SET", key) == "ok";
	}

	public boolean delete(String key) { 
		
		return sendMessage("DELETE", key) == "ok";		
	}

	private String sendMessage(String action, String key) {
		
		String response = null;
		try {
			
			getConnection();
			
			writeMessage(action, key);
			response = readMessage();
			
			closeConnection();
			
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		return response;
	}

	private void closeConnection() throws IOException {
		
		os.close();
        is.close();        
        client.close();
	}

	private void writeMessage(String action, String key) throws IOException {
				
		String message = action + " " + key + " END\n";
		
        os.writeBytes(message);
        logger.log("Wrote: " + message);
	}
	
	private String readMessage() throws IOException {		
		
        String response = is.readLine();		
		logger.log("Read: " + response);
	    
	    return response;
	}

	private void getConnection() throws UnknownHostException, IOException {
		
		client = new Socket(host, port);
				
        os = new DataOutputStream(client.getOutputStream());
        is = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}	
}
