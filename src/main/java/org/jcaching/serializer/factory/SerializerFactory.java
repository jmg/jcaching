package org.jcaching.serializer.factory;

import org.apache.commons.configuration.Configuration;
import org.jcaching.exception.ImplementationClassLoadException;
import org.jcaching.factory.BaseFactory;
import org.jcaching.serializer.Serializer;

public class SerializerFactory extends BaseFactory {
		
	public SerializerFactory(Configuration configuration) {
		
		super(configuration);
	}

	public Serializer getSerializerInstance() throws ImplementationClassLoadException {
		
		return (Serializer) this.getObjectInstance("jcaching.serializer");
	}
}
