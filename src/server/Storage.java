package server;

import java.util.HashMap;

public class Storage {
	
	private static HashMap<String, String> storage = new HashMap<String, String>();
	
	public static HashMap<String, String> getStorage() {
		
		return storage;
	}
	
	public static void set(String key, String value) {
		
		storage.put(key, value);
	}
	
	public static String get(String key) {
		
		return storage.get(key);
	}
	
	public static void delete(String key) {
		
		storage.remove(key);
	}
}
