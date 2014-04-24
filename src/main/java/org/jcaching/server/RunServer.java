/**
 * RunServer
 *
 * TODO Description if available.
 */

package org.jcaching.server;

import java.io.IOException;

import org.jcaching.utils.Logger;
import org.jcaching.utils.ServerLogger;

/**
 * TODO description
 */
public class RunServer {

    /**
     * TODO
     *
     * @param args TODO
     * @throws IOException TODO
     */
    public static void main(String[] args) throws IOException {
        
        ServerLogger.setLevel(Logger.PRODUCTION);
        
        CacheDeamon deamon = CacheDeamon.getInstance();
        deamon.serverForever();
    }

}
