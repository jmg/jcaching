package org.jcaching.config;

public class Loader {
	
	public static Class<?> loadClass(String packageName, String className) {			
		
		Class<?> klass = null;
		try {			
			
			ClassLoader loader = Loader.class.getClassLoader();
			klass = loader.loadClass(packageName + "." + className);
			
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();			
		}
		
		return klass;		
	}
	
	public static Object newInstance(Class<?> klass) {
		
		Object instance = null;
		try {
			instance = klass.newInstance();
		} catch (InstantiationException e) { 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public static Object getNewInstance(String packageName, String className) {
		
		Class<?> klass = Loader.loadClass(packageName, className);
		return newInstance(klass);
	}
}
