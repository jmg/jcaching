package org.jcaching.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.jcaching.protocol.Message;
import org.jcaching.protocol.Protocol;
import org.jcaching.utils.ServerLogger;

public class ClientConnection extends Thread {
	
	private Socket client;
	private BufferedReader is;
	private DataOutputStream os;	
	private Protocol protocol;	

	public ClientConnection(Socket client, Protocol protocol) throws IOException {
		
		this.client = client;
		this.protocol = protocol;
		
		is = new BufferedReader(new InputStreamReader(client.getInputStream()));
        os = new DataOutputStream(client.getOutputStream());
	}
	
	@Override
    public void run() {
        
		Message message;
		try {
			
			message = protocol.parseMessage(readMessage());
			writeMessage(protocol.buildResponse(message));	        
	        closeConnection();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}                   
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
