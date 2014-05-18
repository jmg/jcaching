package org.jcaching.serializer.impl.gsonserializer;

public class SerializedObjectWrapper {
	
	private String object;
	private String type;		
	
	public SerializedObjectWrapper(String object, String type) {
		super();
		this.object = object;
		this.type = type;
	}

	public String getObject() {
		return object;
	}
	
	public void setObject(String object) {
		this.object = object;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}	
}
