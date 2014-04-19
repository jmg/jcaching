package utils;

public class ClientLogger extends Logger {
	
	private static ClientLogger instance = null;
	
	private ClientLogger() {
		
	}
	
	public static Logger getInstance() {
		
		if (instance == null) {
			instance = new ClientLogger();
		}		
		return instance;
	}
	
	@Override 
	public String getOrigin() {
		
		return "Client";
	}
	
}
