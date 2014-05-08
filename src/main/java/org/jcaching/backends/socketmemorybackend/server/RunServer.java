/**
 * RunServer
 *
 * TODO Description if available.
 */

package org.jcaching.backends.socketmemorybackend.server;

import java.io.IOException;

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
        
        CacheDeamon deamon = CacheDeamon.getInstance();
        deamon.serverForever();
    }

}
