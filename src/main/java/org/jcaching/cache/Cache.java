package org.jcaching.cache;

import org.jcaching.backends.CacheBackend;
import org.jcaching.config.Config;

/*
 * Facade to interact with the cache user
 * */
public class Cache {		
	
	private static CacheBackend getBackend() {
		
		return Config.getInstance().getBackend();
	}
		
	public static Object get(String key) {
		
		return getBackend().get(key);
	}
	
	public static void set(String key, String value) {
		
		getBackend().set(key, value);
	}
	
	public static void set(String key, String value, int timeout) {
		
		getBackend().set(key, value, timeout);
	}

	public static void delete(String key) {
		
		getBackend().delete(key);
	}

	public static void setConfigValue(String key, String value) { 
		
		Config.getInstance().setConfigValue(key, value);
	}
}
