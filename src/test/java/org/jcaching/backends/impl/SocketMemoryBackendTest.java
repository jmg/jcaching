/**
 * MemoryProcessBackendTest
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl;

import junit.framework.TestCase;

import org.jcaching.backends.socketmemorybackend.server.CacheDeamon;
import org.jcaching.cache.Cache;
import org.junit.Test;

/**
 * TODO description
 */
public class SocketMemoryBackendTest extends TestCase {      
        
    public void setUp() {
         
    	Cache.setConfigValue("backend", "SocketMemoryBackend");
    	
        CacheDeamon.serverForeverInAThread();
        
        //Workaround: Wait until the server starts
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {      
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCacheSet() {
        
        Cache.set("key-1", "test");
    }
    
    @Test
    public void testCacheGet() {
        
    	Cache.set("key-1", "test value");
        assertEquals("test value", Cache.get("key-1"));
    }
    
    @Test
    public void testCacheDelete() {
        
    	Cache.set("key-1", "test");
        Cache.delete("key-1");
        assertEquals(null, Cache.get("key-1"));
    }
}
