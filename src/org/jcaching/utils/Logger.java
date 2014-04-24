package org.jcaching.utils;

public class Logger {
	
	protected static int level = 0;
	
	public static int DEBUG = 1;
	public static int PRODUCTION = 0;
	
	public static void setLevel(int level) {
		
		Logger.level = level;
	}
	
	public static int getLevel() {
		
		return level;
	}
		
	public String getOrigin() {
		
		return "";
	}
	
	public void doLog(int level, Object obj) {
		
		if (Logger.level > PRODUCTION) {
			
			System.out.println(getOrigin() + obj.toString());
		}
	}	
}
