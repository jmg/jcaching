package org.jcaching.serializer.impl.serializer;

import java.lang.reflect.Field;

import org.jcaching.serializer.Serializer;

// Cambiar el nombre de esta clase a gusto. Pero que no se llame como la interfaz Serializer.
public class SerializerImpl implements Serializer {

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
		int beginIndex;
		int endIndex;
		Object finalObject = null;
		
		beginIndex = serializedObject.indexOf("Class:");
		endIndex = serializedObject.substring(beginIndex).indexOf("\n");
		String auxClass = serializedObject.substring(beginIndex).substring(6, endIndex);
		
		try {
			finalObject = instanciarObjeto(auxClass);
		} catch (Exception e) {}
				
		
		return finalObject;
	}
	
	public Object instanciarObjeto(String nombreClase) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		Class<?> clazz = null;
		
			clazz= Class.forName(nombreClase);
			
			//obtenerInstancia();
					
			return clazz.newInstance();
	}

}