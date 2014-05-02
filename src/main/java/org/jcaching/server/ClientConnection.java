package org.jcaching.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.jcaching.protocol.Message;
import org.jcaching.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientConnection extends Thread {

    private static Logger logger = LoggerFactory.getLogger(ClientConnection.class);
	
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
        logger.debug("Read: {}", response);
        
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
        logger.debug("Wrote: {}", message);                
    }
}
