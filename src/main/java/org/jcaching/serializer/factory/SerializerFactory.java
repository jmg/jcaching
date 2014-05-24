package org.jcaching.serializer.factory;

import org.apache.commons.configuration.Configuration;
import org.jcaching.factory.BaseFactory;
import org.jcaching.serializer.Serializer;

public class SerializerFactory<T extends Serializer> extends BaseFactory<T> {
		
	public SerializerFactory(Class<T> t, Configuration configuration) {
		
		super(t, configuration);
	}
	
	@Override
	protected String getConfigurationKey() {
 
		return "jcaching.serializer";
	}	
}
