package org.jcaching.backends;

import client.CacheClient;

public class MemoryProcessBackend implements CacheBackend {
	
	CacheClient client = new CacheClient();

	@Override
	public boolean set(String key, String serializedObj) {

		return client.set(key, serializedObj);
	}

	@Override
	public boolean set(String key, String serializedObj, int timeout) {

		return client.set(key, serializedObj);
	}

	@Override
	public String get(String key) {
		
		return client.get(key);
	}

	@Override
	public boolean delete(String key) {
		
		return client.delete(key);
	}

}
