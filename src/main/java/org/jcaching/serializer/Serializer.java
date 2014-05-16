package org.jcaching.serializer;

import java.lang.reflect.Field;

public class Serializer {

	public String serialize(Object serializableObject){
		String finalString ="{ ";
		Class<?> clazz = serializableObject.getClass();
		
		//Save the name of the class
		String objectClass = clazz.getName();
		finalString+="Class:"+objectClass+"\n";
		
		//Sabe the attr
		Field attrs[] = clazz.getDeclaredFields(); //Que onda? http://docs.oracle.com/javase/tutorial/reflect/class/classMembers.html
		
		
		for( Field attr : attrs){
			boolean accessible = attr.isAccessible();
			attr.setAccessible(true);
			try {finalString += attr.getType()+" "+attr.getName() +":"+attr.get(serializableObject)+"\n" ;} catch(IllegalAccessException e){}
			attr.setAccessible(accessible);
		}
		
		finalString +="}";
		return finalString;
	}
	
	

}
