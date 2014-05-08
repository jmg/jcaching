package org.jcaching.examples;

import org.jcaching.cache.Cache;

public class Simple {
	
	public static void main(String[] args) {
		
		System.out.println(getFromCache());
	}
	
	public static String getFromCache() {
		
		String value = (String) Cache.get("key-1");
		if (value == null) {
			/* The value assosiated with key-1 wasn't in cache. 
			   Set the value within the key for the next time. */
			value = "my value";
			Cache.set("key-1", value);
			System.out.println("Set " + value + " on cache!");
		} else {
			/* The value assosiated with key-1 was in cache. Bingo! */
			System.out.println("Read " + value + " from cache!");
		}
		return value;
	}
}
