package org.jcaching.serializer.impl.serializer;

import java.lang.reflect.Field;

import org.jcaching.serializer.ISerializer;

public class Serializer implements ISerializer {
	
	@Override
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
	
	@Override
	public Object deserialize(String serializedObject){
		
		Object finalObject = null;
		
		
		
		
		return finalObject;
	}
	
	public Object instanciarObjeto(String serializedObject){
		int beginIndex;
		int endIndex;
		Class<?> clazz = null;
		
		
		beginIndex = serializedObject.indexOf("Class:");
		endIndex = serializedObject.substring(beginIndex).indexOf("\n");
		String auxClass = serializedObject.substring(beginIndex).substring(6, endIndex);
		try {
			clazz= Class.forName(auxClass);
			return clazz.newInstance();
		} catch (Exception e){return null;}
	}

}
