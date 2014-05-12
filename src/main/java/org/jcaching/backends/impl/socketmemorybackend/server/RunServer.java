/**
 * RunServer
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.socketmemorybackend.server;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.jcaching.JCaching;
import org.jcaching.exception.ImplementationClassLoadException;

/**
 * TODO description
 */
public class RunServer {

    /**
     * TODO
     *
     * @param args TODO
     * @throws ImplementationClassLoadException TODO
     * @throws ConfigurationException TODO
     * @throws IOException TODO
     */
    public static void main(String[] args) throws ConfigurationException,
            ImplementationClassLoadException, IOException {
        
        CacheDeamon deamon = new CacheDeamon(JCaching.getConfiguration());
        deamon.serverForever();
    }

}
