package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import protocol.Protocol;

import utils.ClientLogger;

public class CacheClient {
	
	Integer defaultPort = 22122;
	String host = "localhost";
	Integer port;
	
	Socket client;
	
	DataOutputStream os;
	BufferedReader is;
	
	public CacheClient() {
		
		this.port = defaultPort;
	}
	
	public CacheClient(int port) {
		
		this.port = port;
	}
	
	public String get(String key) {
		                
		return sendMessage("GET", key, null);      			
	}
	
	public boolean set(String key, String data) {
		
		return sendMessage("SET", key, data).equals("ok");
	}

	public boolean delete(String key) { 
		
		return sendMessage("DELETE", key, null).equals("ok");		
	}

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

	private void closeConnection() throws IOException {
		
		os.close();
        is.close();        
        client.close();
	}

	private void writeMessage(String action, String key, String data) throws IOException {
				
		String message = Protocol.buildMessage(action, key, data); 				
        os.writeBytes(message);
        
        ClientLogger.log("Wrote: " + message);
	}
	
	private String readMessage() throws IOException {		
		
        String response = is.readLine();
        ClientLogger.log("Read: " + response);
	    
	    return response;
	}

	private void getConnection() throws UnknownHostException, IOException {
		
		client = new Socket(host, port);
				
        os = new DataOutputStream(client.getOutputStream());
        is = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}	
}
