package tests;

import org.junit.Before;
import org.junit.Test;
import server.CacheDeamon;
import utils.ClientLogger;
import utils.ServerLogger;
import backends.MemoryProcessBackend;
import junit.framework.TestCase;

public class MemoryProcessBackendTest extends TestCase {
	
	MemoryProcessBackend backend = new MemoryProcessBackend();
		
	public void setUp() {
		
		final int debug = 1;
		ClientLogger.getInstance().setLevel(debug);
		ServerLogger.getInstance().setLevel(debug);
		
		CacheDeamon.serverForeverInAThread();
		
		//wait until server starts
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {		
			e.printStackTrace();
		}
	}
		
	public void testCacheSet() {
		
		assertEquals(true, backend.set("key-1", "test"));
	}
	
	
	public void _testCacheGet() {
		
		backend.set("key-1", "test");
		assertEquals("test", backend.get("key-1"));
	}
	
	
	public void _testCacheDelete() {
		
		backend.set("key-1", "test");
		backend.delete("key-1");
		assertEquals(null, backend.get("key-1"));
	}
}
