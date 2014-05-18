package org.jcaching.serializer;

public interface ISerializer {
	
	public String serialize(Object object);
    public Object deserialize(String serializedObject);
}
