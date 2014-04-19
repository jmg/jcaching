package utils;

public class ServerLogger extends Logger {
	
	private static ServerLogger instance = null;
	
	private ServerLogger() {
		
	}
	
	public static ServerLogger getInstance() {
		
		if (instance == null) {
			instance = new ServerLogger();
		}		
		return instance;
	}
	
	@Override 
	public String getOrigin() {
		
		return "Server";
	}
}
