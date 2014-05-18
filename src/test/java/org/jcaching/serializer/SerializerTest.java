/**
 * SerializerTest
 *
 * Test units for Serializer class behaviour.
 */

package org.jcaching.serializer;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializerTest {
	
	Serializer aSerializer = new Serializer();
	String aString = "There are no facts, only interpretations";
	int aInt = 4;
	
	@Before
	public void setUp() throws Exception 
	{
	}
	
	public class prueba {
	
	}
	
	
	@Test
	public void puedoGuardarLaClaseString(){

		String serializedObject = aSerializer.serialize(aString);
		Assert.assertTrue(serializedObject.contains("Class:java.lang.String"));
		
	}
	
	@Test
	public void puedoGuardarLaClaseInt(){

		String serializedObject = aSerializer.serialize(aInt);
		Assert.assertTrue(serializedObject.contains("Class:java.lang.Integer"));
		
	}

	@Test
	public void puedoLevantarLaClaseString() throws Exception{
		String serializedObject= "{ Class:java.lang.String\n}";
		String finalObject = (String) aSerializer.instanciarObjeto(serializedObject);
		Assert.assertTrue(finalObject.getClass() == Class.forName("java.lang.String"));
	}
	
	@Test
	public void puedoLevantarLaClaseInteger() throws Exception{
		String serializedObject= "{ Class:java.lang.Integer\n}";
		Integer finalObject = (Integer) aSerializer.instanciarObjeto(serializedObject);
		Assert.assertTrue(finalObject.getClass() == Class.forName("java.lang.Integer"));
	}
	
	@Test
	public void serializarDeserializarString()
	{
		//String serializaded = serializer.serialize(new prueba("holi"));
		//Object deserializaded = serializer.deserialize(serializaded); 
		//Assert.assertEquals(deserializaded,stringSerializable);
		
	}

	

}
