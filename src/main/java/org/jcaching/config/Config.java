package org.jcaching.config;

import java.util.HashMap;

import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.socketmemorybackend.protocol.Protocol;

/**
 * Loads the configuration file and centralizes the access 
 * to the configuration parameters for the whole application. 
 */
public class Config {
	
	private String IMPL_PACKAGE_PREFIX = "impl";
	private static Config instance;	
	HashMap<String, String> configValues = new HashMap<String, String>();
	
	CacheBackend backend;
	Protocol protocol;
	
	public static Config getInstance() {
		
		if (instance == null) {
			instance = new Config(); 
		}
		return instance;
	}
	
	/*
	* @todo Load the configuration from a file. 
	* For more info see https://github.com/jmg/jcaching/issues/1
	*/  
	protected Config() {
		
		configValues = getConfigValues();
	}	
	
	/*
	* @todo Return the proper configuration values from the configuration file.
	*/  
	private HashMap<String, String> getConfigValues() {
			
		//Hardcoded config backend
		configValues.put("backend", "SocketMemoryBackend");
			
		//Hardcoded config for memory process backend
		configValues.put("host", "localhost");
		configValues.put("port", "22122");
		configValues.put("protocol", "SimpleProtocol");
		
		return configValues;
	}
	
	public void setConfigValue(String key, String value) {
		
		configValues.put(key, value);	
	}
	
	public CacheBackend getBackend() {
			
		if (backend == null) {
			backend = (CacheBackend) Loader.newInstance(CacheBackend.class.getPackage().getName() + "." + IMPL_PACKAGE_PREFIX, configValues.get("backend"));
			backend.setConfig(this);
		}		
		return backend;		
	}
	
	public Protocol getProtocol() {
			
		if (protocol == null) {
			protocol = (Protocol) Loader.newInstance(Protocol.class.getPackage().getName() + "." + IMPL_PACKAGE_PREFIX, configValues.get("protocol"));
		}
		return protocol;
	}

	public Integer getPort() {
		
		return Integer.parseInt(configValues.get("port"));
	}
	
	public String getHost() {
		
		return configValues.get("host");
	}

}
