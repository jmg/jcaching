package org.jcaching.utils;

public class ServerLogger extends Logger {	
	
	@Override
	public String getOrigin() {
		
		return "Server -> ";
	}
	
	public static void log(Object obj) {
		
		new ServerLogger().doLog(level, obj);				
	}
}
