package backends;

public interface CacheBackend {
	
	/* Set a serialized object in cache using a key and returns true in case of success */
	public boolean set(String key, String serializedObj);
	public boolean set(String key, String serializedObj, int timeout);
	
	/* Returns the serialized object from cache */
	public String get(String key);
	
	/* Delete a serialized object from cache using a key */
	public boolean delete(String key);
}
