package org.jcaching.storage;

public interface StorageObject {

	boolean expired();
	String getValue();
}
