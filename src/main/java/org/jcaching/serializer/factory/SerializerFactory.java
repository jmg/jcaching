package org.jcaching.serializer.factory;

import org.apache.commons.configuration.Configuration;
import org.jcaching.factory.BaseFactory;
import org.jcaching.serializer.Serializer;

public class SerializerFactory<T extends Serializer> extends BaseFactory<T> {
		
	public SerializerFactory(Configuration configuration) {
		
		super(configuration);
	}
	
	@Override
	protected String getConfigurationKey() {
 
		return "jcaching.serializer";
	}	
}
