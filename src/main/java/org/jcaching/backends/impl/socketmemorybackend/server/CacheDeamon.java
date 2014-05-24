/**
 * CacheDeamon
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.impl.socketmemorybackend.protocol.Protocol;
import org.jcaching.backends.impl.socketmemorybackend.protocol.factory.ProtocolFactory;
import org.jcaching.exception.ImplementationClassLoadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO description
 */
public class CacheDeamon {

    /**
     * The property key name containing the value for port number.
     */
    public static final String CONFIGURATION_PORT_KEY = "jcaching.backends.socketmemorybackend.port";

    /**
     * The default value for port number if key is not defined on configuration
     * file.
     */
    public static final int CONFIGURATION_PORT_DEFAULT_VALUE = 22122;

    private static Logger logger = LoggerFactory.getLogger(CacheDeamon.class);

    private Integer port;
    private ServerSocket server;

    private boolean running;
    private Protocol protocol;

    private Configuration configuration;

    /**
     * Constructor.
     *
     * @throws ImplementationClassLoadException TODO
     */
    public CacheDeamon(Configuration configuration)
            throws ImplementationClassLoadException {

        initialize(configuration);
    }

    /**
     * TODO
     *
     * @param configuration TODO
     * @throws ImplementationClassLoadException
     */
    private void initialize(Configuration configuration)
            throws ImplementationClassLoadException {

        this.configuration = configuration;

        port = configuration.getInt(CONFIGURATION_PORT_KEY,
                CONFIGURATION_PORT_DEFAULT_VALUE);

        ProtocolFactory<Protocol> factory = new ProtocolFactory<Protocol>(Protocol.class, configuration);
        protocol = factory.getObjectInstance();
    }

    /**
     * TODO
     * @throws IOException TODO
     */
    public void serverForever() throws IOException {

        if (isRunning()) {
            logger.info("Server is already running.");
            return;
        }

        server = new ServerSocket(port);
        logger.info("Cache server running on: {}", port);

        setRunning(true);

        while (isRunning()) {
            Socket client = acceptConnections();
            new ClientConnection(client, protocol).start();
        }
    }

    /**
     * TODO
     *
     * @param configuration TODO
     */
    public void serverForeverInAThread() {

        Thread t = new Thread(new Runnable() {
            public void run() {

                logger.info(">> Initializing threaded server deamon ...");

                CacheDeamon deamon = null;
                try {
                    deamon = new CacheDeamon(configuration);

                } catch (ImplementationClassLoadException e) {
                    logger.error("Unexpected exception on threaded " +
                            "server deamon:", e);
                }

                logger.info("Done :). Now serving forever... ");

                try {
                    deamon.serverForever();
                    logger.info(">> Done; threaded server has finished " +
                            "(forever has ended).");
 
                } catch (IOException e) {
                    logger.error("Unexpected exception on threaded " +
                            "server deamon when serving content:", e);
                }
           };
        });

        t.start();
    }

    /**
     * Stops the inner server execution.
     */
    public void stopServer() {
        setRunning(false);
    }

    /**
     * @return The flag value that indicates if server is running or not.
     */
    public synchronized boolean isRunning() {
        return running;
    }

    /**
     * TODO
     * @return TODO
     * @throws IOException TODO
     */
    private Socket acceptConnections() throws IOException {
        
        Socket client = server.accept();        

        logger.info("Connection received from {}",
            client.getInetAddress().getHostName());
        
        return client;
    }

    /**
     * @param running The boolean value about the server's running state.
     */
    private synchronized void setRunning(boolean running) {
        this.running = running;
    }
}
