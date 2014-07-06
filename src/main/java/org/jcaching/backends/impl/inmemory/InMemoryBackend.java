package org.jcaching.backends.impl.inmemory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.Configuration;
import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.impl.BaseCacheBackend;
import org.jcaching.backends.impl.inmemory.valuewrapper.DummyValueWrapper;
import org.jcaching.backends.impl.inmemory.valuewrapper.TimeoutValueWrapper;
import org.jcaching.backends.impl.inmemory.valuewrapper.ValueWrapper;

public class InMemoryBackend extends BaseCacheBackend implements CacheBackend {

	private static final Map<String, ValueWrapper<Object>>	map	= new ConcurrentHashMap<String, ValueWrapper<Object>>();

	public InMemoryBackend(Configuration configuration) {
		super(configuration);
	}

	@Override
	public void set(String key, Object value) {
		map.put(key, new DummyValueWrapper<Object>(value));
	}

	@Override
	public void set(String key, Object value, int timeout) {
		map.put(key, new TimeoutValueWrapper<Object>(value, timeout));
	}

	@Override
	public Object get(String key) {
		ValueWrapper<Object> v = map.get(key);
		if(v == null)
			return null;
		if(!v.isValid())
			return null;

		return v.getValue();
	}

	@Override
	public void delete(String key) {
		map.remove(key);
	}


}
