/**
 * CacheClient
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.backends.impl.socketmemorybackend.protocol.exception.InvalidActionException;
import org.jcaching.backends.impl.socketmemorybackend.protocol.factory.ProtocolFactory;
import org.jcaching.backends.impl.socketmemorybackend.server.CacheDeamon;
import org.jcaching.exception.ImplementationClassLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO description
 */
public class CacheClient {

    /**
     * The property key name containing the value for host name.
     */
    public static final String CONFIGURATION_HOST_KEY =
        "jcaching.backends.socketmemorybackend.host";

    /**
     * The default value for host if key is not present.
     */
    public static final String CONFIGURATION_HOST_DEFAULT_VALUE = "localhost";

    private static Logger logger = LoggerFactory.getLogger(CacheClient.class);

    private String host;
    private Integer port;

    private Socket client;

    private DataOutputStream os;
    private BufferedReader is;
    private Protocol protocol;

    private Configuration configuration;

    /**
     * Constructor.
     *
     * @param configuration The configuration object containing the
     * parametrization values.
     * @throws ImplementationClassLoadException The protocol class defined on
     * configuration file cannot be loaded.
     */
    public CacheClient(Configuration configuration)
            throws ImplementationClassLoadException {

        this.configuration = configuration;
        initialize();
    }

    /**
     * Initializes required values for client and instantiates the protocol
     * indicated.
     *
     * @throws ImplementationClassLoadException The protocol class defined on
     * configuration file is unable to be loaded.
     */
    public void initialize() throws ImplementationClassLoadException {
        
        ProtocolFactory<Protocol> factory = new ProtocolFactory<Protocol>(Protocol.class, configuration);
        protocol = factory.getObjectInstance();

        port = configuration.getInteger(CacheDeamon.CONFIGURATION_PORT_KEY,
            CacheDeamon.CONFIGURATION_PORT_DEFAULT_VALUE);
        
        host = configuration.getString(CONFIGURATION_HOST_KEY,
            CONFIGURATION_HOST_DEFAULT_VALUE);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param klass 
     * @return TODO
     */
    public String get(String key) {
                        
        return sendMessage(protocol.getGetAction(), key, null, null);               
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param data TODO
     * @return TODO
     */
    public void set(String key, String data) {
        
        sendMessage(protocol.getSetAction(), key, data, null);
    }
    
    /**
     * TODO
     *
     * @param key TODO
     * @param data TODO
     * @return TODO
     */
    public void set(String key, String data, int timeout) {
        
        sendMessage(protocol.getSetAction(), key, data, timeout);
    }

    /**
     * TODO
     *
     * @param key TODO
     * @return TODO
     */
    public void delete(String key) { 
        
        sendMessage(protocol.getDeleteAction(), key, null, null);       
    }

    /**
     * TODO
     *
     * @param action TODO
     * @param key TODO
     * @param data TODO
     * @return TODO
     */
    private String sendMessage(String action, String key, String data, Integer timeout) {
        
        String response = null;
        try {
            
            getConnection();
            
            writeMessage(protocol.buildClientMessage(action, key, data, timeout));
            response = protocol.parseServerResponse(readMessage());
            
            closeConnection();
            
        } catch (IOException e) { 
            e.printStackTrace();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
 
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
     * @param action TODO
     * @param key TODO
     * @param data TODO
     *
     * @throws IOException TODO
     */
    private void writeMessage(String message) throws IOException {
                               
        os.writeBytes(message);
        
        logger.debug("Wrote: {}", message);
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
     * @throws UnknownHostException TODO
     * @throws IOException TODO
     */
    private void getConnection() throws UnknownHostException, IOException {
        
        client = new Socket(host, port);
                
        os = new DataOutputStream(client.getOutputStream());
        is = new BufferedReader(
            new InputStreamReader(client.getInputStream())
        );
    }
}
