package tests;

import org.junit.Test;

import backends.MemoryProcessBackend;

import junit.framework.TestCase;

public class MemoryProcessBackendTest extends TestCase {
	
	MemoryProcessBackend backend = new MemoryProcessBackend();
	
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
		backend.delete("key-1");
		assertEquals(null, backend.get("key-1"));
	}
}
