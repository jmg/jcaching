/**
 * ClientConnection
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.jcaching.backends.impl.socketmemorybackend.protocol.Message;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 */
public class ClientConnection extends Thread {

    private static Logger logger = LoggerFactory.getLogger(ClientConnection.class);
    
    private Socket client;
    private BufferedReader is;
    private DataOutputStream os;    
    private Protocol protocol;  

    /**
     * Constructor.
     *
     * @param client TODO
     * @param protocol TODO
     * @throws IOException TODO
     */
    public ClientConnection(Socket client, Protocol protocol) throws IOException {
        
        this.client = client;
        this.protocol = protocol;
        
        is = new BufferedReader(new InputStreamReader(client.getInputStream()));
        os = new DataOutputStream(client.getOutputStream());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        
        Message message;
        try {
            
            message = protocol.parseMessage(readMessage());
            writeMessage(protocol.buildResponse(message));          
            closeConnection();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }                   

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
