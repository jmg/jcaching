package org.jcaching.backends.impl.memory.valuewrapper;

public abstract class ValueWrapper<T> {
	protected T		value;
	
	public T getValue() {
		return value;
	}
	public abstract boolean isValid();
}
