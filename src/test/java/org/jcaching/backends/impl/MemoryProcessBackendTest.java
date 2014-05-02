/**
 * MemoryProcessBackendTest
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl;

import junit.framework.TestCase;

import org.jcaching.server.CacheDeamon;
import org.junit.Test;

/**
 * TODO description
 */
public class MemoryProcessBackendTest extends TestCase {
    
    MemoryProcessBackend backend = new MemoryProcessBackend();
        
    public void setUp() {
                
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
        
        assertEquals(true, backend.set("key-1", "test"));
    }
    
    @Test
    public void testCacheGet() {
        
        backend.set("key-1", "test value");
        assertEquals("test value", backend.get("key-1"));
    }
    
    @Test
    public void testCacheDelete() {
        
        backend.set("key-1", "test");
        assertEquals(true, backend.delete("key-1"));
        assertEquals(null, backend.get("key-1"));
    }
}
