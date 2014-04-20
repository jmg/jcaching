package tests;

import org.junit.Test;
import server.CacheDeamon;
import utils.ClientLogger;
import utils.Logger;
import utils.ServerLogger;
import backends.MemoryProcessBackend;
import junit.framework.TestCase;

public class MemoryProcessBackendTest extends TestCase {
	
	MemoryProcessBackend backend = new MemoryProcessBackend();
		
	public void setUp() {
				
		ClientLogger.setLevel(Logger.DEBUG);
		ServerLogger.setLevel(Logger.DEBUG);
		
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
		
		backend.set("key-1", "test");
		assertEquals("test", backend.get("key-1"));
	}
	
	@Test
	public void testCacheDelete() {
		
		backend.set("key-1", "test");
		assertEquals(true, backend.delete("key-1"));
		assertEquals("null", backend.get("key-1"));
	}
}
