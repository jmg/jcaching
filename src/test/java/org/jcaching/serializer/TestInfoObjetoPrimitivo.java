package org.jcaching.serializer;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestInfoObjetoPrimitivo {

	Serializer aSerializer = new Serializer();
	String aString = "There are no facts, only interpretations";
	Integer aInteger = 4;
	Boolean aBoolean= true;
	
	@Test
	public void puedoLevantarUnString() throws Exception{
		
		InfoObjeto infoObjeto = new InfoObjetoString(aString);
		Object finalObject = infoObjeto.retornarInstancia();
		
		Assert.assertTrue(finalObject.getClass() == Class.forName("java.lang.String"));
		Assert.assertTrue(finalObject == aString);
	}
	
	@Test
	public void puedoLevantarLaClaseInteger() throws Exception{
		
		InfoObjeto infoObjeto = new InfoObjetoInteger(aInteger.toString());
		Object finalObject = infoObjeto.retornarInstancia();
		
		Assert.assertTrue(finalObject.getClass() == Class.forName("java.lang.Integer"));
		Assert.assertTrue(finalObject == aInteger);
	}
	
	@Test
	public void puedoLevantarLaClaseBoolean() throws Exception{
		
		InfoObjeto infoObjeto = new InfoObjetoBoolean(aBoolean.toString());
		Object finalObject = infoObjeto.retornarInstancia();
		
		Assert.assertTrue(finalObject.getClass() == Class.forName("java.lang.Boolean"));
		Assert.assertTrue(finalObject == aBoolean);
	}
	
	

}
