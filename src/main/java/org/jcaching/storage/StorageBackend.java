package org.jcaching.storage;

public interface StorageBackend {
	
	public void set(String key, StorageObject value);
	public String get(String key);
	public void delete(String key);
}
