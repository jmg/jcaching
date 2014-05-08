package org.jcaching.serializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class SerializerTest {
	
	String stringSerializable;
	Serializer serializer;
	
	@Before
	public void setUp() throws Exception 
	{
		stringSerializable = "Esto es un string";
		serializer = new Serializer();
		//objetoConListaDeString = new ;
	}
	

	
	@Test
	public void serializarDeserializarString()
	{
		//String serializaded = serializer.serialize(stringSerializable);
		//String deserializaded = serializer.deserialize(serializaded); 
		//Assert.assertEquals(deserializaded,stringSerializable);
		
	}

	

}
