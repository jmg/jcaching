package org.jcaching.serializer.impl.gsonserializer;

import org.jcaching.serializer.ISerializer;

import com.google.gson.Gson;

public class GsonSerializer implements ISerializer {
	
	@Override
	public String serialize(Object object) {
  	  
    	String serializedObj = new Gson().toJson(object);
    	SerializedObjectWrapper serializedObjectWrapper = new SerializedObjectWrapper(serializedObj, object.getClass().getName()); 
    	return new Gson().toJson(serializedObjectWrapper);
    }
    	
	@Override
    public Object deserialize(String serializedObject) {
		
		if (serializedObject == null) {
			return null;
		}
    	    	
    	SerializedObjectWrapper serializedObjectWrapper = new Gson().fromJson(serializedObject, SerializedObjectWrapper.class);
    	
    	Class<?> klass = null;
		try {
			klass = Class.forName(serializedObjectWrapper.getType());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
    	return new Gson().fromJson(serializedObjectWrapper.getObject(), klass);
    }
}
