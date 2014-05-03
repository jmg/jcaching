/**
 * MemoryProcessBackendTest
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl;

import junit.framework.TestCase;

import org.jcaching.cache.Cache;
import org.jcaching.server.CacheDeamon;
import org.junit.Test;

/**
 * TODO description
 */
public class MemoryProcessBackendTest extends TestCase {      
        
    public void setUp() {
         
    	Cache.forceCacheBackend("MemoryProcessBackend");
    	
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
        
        assertEquals(true, Cache.set("key-1", "test"));
    }
    
    @Test
    public void testCacheGet() {
        
    	Cache.set("key-1", "test value");
        assertEquals("test value", Cache.get("key-1"));
    }
    
    @Test
    public void testCacheDelete() {
        
    	Cache.set("key-1", "test");
        assertEquals(true, Cache.delete("key-1"));
        assertEquals(null, Cache.get("key-1"));
    }
}
