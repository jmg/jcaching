package utils;

public class Logger {
	
	private int level = 0;	
	
	public String getOrigin() {
		
		return "";
	}

	public void setLevel(int level) {
				
		this.level = level;
	}
	
	public void log(Object obj) {
		
		if (level > 0) {			
			System.out.println(this.getOrigin() + " -> " + obj.toString());
		}
	}
	
}
