package org.jcaching.serializer.factory;

import org.apache.commons.configuration.Configuration;
import org.jcaching.serializer.Serializer;
import org.jcaching.serializer.impl.gsonserializer.GsonSerializer;

public class SerializerFactory {
	
	/* TODO implement a factory to instantiate 
	   serializer objects from the configuration file.
	*/
	
	private Configuration configuration;	
	
	public SerializerFactory(Configuration configuration) {
		
		this.configuration = configuration;
	}

	public Serializer getSerializerInstance() {
		
		// FIXME Hardcoded serializer
		return new GsonSerializer();
	}
}
