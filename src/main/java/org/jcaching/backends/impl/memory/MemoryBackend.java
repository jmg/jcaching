package org.jcaching.backends.impl.memory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.impl.memory.valuewrapper.DummyValueWrapper;
import org.jcaching.backends.impl.memory.valuewrapper.TimeoutValueWrapper;
import org.jcaching.backends.impl.memory.valuewrapper.ValueWrapper;
import org.jcaching.config.Config;

public class MemoryBackend implements CacheBackend {
	private static final Map<String, ValueWrapper<Object>>	map	= new ConcurrentHashMap<String, ValueWrapper<Object>>();

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

	@Override
	public void setConfig(Config config) {}

}
