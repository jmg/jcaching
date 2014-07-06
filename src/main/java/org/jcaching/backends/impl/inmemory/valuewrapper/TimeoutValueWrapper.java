package org.jcaching.backends.impl.inmemory.valuewrapper;

public class TimeoutValueWrapper<T> extends ValueWrapper<T> {
	private long	expires;

	public TimeoutValueWrapper(T value, int timeout) {
		this.value = value;
		expires = System.currentTimeMillis() + timeout*1000;
	}

	@Override
	public boolean isValid() {
		return System.currentTimeMillis() < expires;
	}

}