package org.jcaching.backends.impl.inmemory.valuewrapper;

public class DummyValueWrapper<T> extends ValueWrapper<T> {
	public DummyValueWrapper(T value) {
		super();
		this.value = value;
	}
	@Override
	public boolean isValid() {
		return true;
	}
}