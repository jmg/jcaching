package protocol;

public class Message {
		
	private Action action;
	private String key;
	private String value;	
	
	public Message(String action, String key, String value) {
		super();
				
		this.key = key;
		this.value = value;
		this.action = this.setAction(action);
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Action getAction() {
		return action;
	}
	public Action setAction(String action) {
		
		if (action.equals("GET")) {
			return new Get();
		} else if (action.equals("SET")) {
			return new Set();
		} else if (action.equals("DELETE")) {
			return new Delete();
		}
		return null;
	}
	
}
