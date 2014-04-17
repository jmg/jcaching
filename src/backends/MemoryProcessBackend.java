package backends;

import clientserver.CacheClient;

public class MemoryProcessBackend implements CacheBackend {
	
	CacheClient client = new CacheClient();

	@Override
	public boolean set(String key, String serializedObj) {

		return client.setData(key, serializedObj);
	}

	@Override
	public boolean set(String key, String serializedObj, int timeout) {

		return client.setData(key, serializedObj);
	}

	@Override
	public String get(String key) {
		
		return client.getData(key);
	}

	@Override
	public boolean delete(String key) {
		
		return client.deleteData(key);
	}

}
