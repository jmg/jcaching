package org.jcaching.cache;

import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.impl.MemoryProcessBackend;
import org.jcaching.config.Config;

/*
 * Facade to interact with the cache user
 * */
public class Cache {		
	
	private static CacheBackend getBackend() {
		
		return Config.getInstance().getBackend();
	}
		
	public static String get(String key) {
		
		return getBackend().get(key);
	}
	
	public static boolean set(String key, String value) {
		
		return getBackend().set(key, value);
	}
	
	public static boolean set(String key, String value, int timeout) {
		
		return getBackend().set(key, value, timeout);
	}

	public static boolean delete(String key) {
		
		return getBackend().delete(key);
	}

	public static void forceCacheBackend(String backend) { 
		
		Config.getInstance().setConfigValue("backend", backend);
	}
}
