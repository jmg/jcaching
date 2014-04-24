package org.jcaching.utils;

public class ClientLogger extends Logger {		
	
	@Override
	public String getOrigin() {
		
		return "Client -> ";
	}
	
	public static void log(Object obj) {
		
		new ClientLogger().doLog(level, obj);				
	}
}
