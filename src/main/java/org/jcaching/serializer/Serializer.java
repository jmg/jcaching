package org.jcaching.serializer;

import java.lang.reflect.Field;

public class Serializer {

	public String serialize(Object serializableObject) throws ClassNotFoundException {
		String finalString ="{ ";
		
		//Save the name of the class
		String objectClass = serializableObject.getClass().toString();
		finalString+="Class:"+objectClass+"";
		
		//Sabe the attr
		Class<?> clazz = Class.forName(objectClass);
		Field attrs[] = clazz.getDeclaredFields(); //Que onda?
		
		finalString +="\n}";
		return finalString;
	}

}
