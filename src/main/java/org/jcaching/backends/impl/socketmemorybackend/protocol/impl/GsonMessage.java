package org.jcaching.backends.impl.socketmemorybackend.protocol.impl;

public class GsonMessage {
	
	private String action;
	private String key;
	private String data;
	private Integer timeout;
		
	public GsonMessage(String action, String key, String data, Integer timeout) {
		super();
		this.action = action;
		this.key = key;
		this.data = data;
		this.timeout = timeout;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
}
