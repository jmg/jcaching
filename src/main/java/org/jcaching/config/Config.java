package org.jcaching.config;

import java.util.HashMap;

import org.jcaching.backends.CacheBackend;
import org.jcaching.protocol.Protocol;

/**
 * Loads the configuration file and centralizes the access 
 * to the configuration parameters for the whole application. 
 */
public class Config {
	
	private static Config instance;	
	HashMap<String, String> configValues = new HashMap<String, String>();
	
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
	private Config() {
		
		configValues = getConfigValues();
	}	
	
	/*
	* @todo Return the proper configuration values from the configuration file.
	*/  
	private HashMap<String, String> getConfigValues() {
			
		//Hardcoded config backend
		configValues.put("backend", "MemoryProcessBackend");
			
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
		
		return (CacheBackend) Loader.getNewInstance("org.jcaching.backends.impl", configValues.get("backend"));		
	}
	
	public Protocol getProtocol() {
							
		return (Protocol) Loader.getNewInstance("org.jcaching.protocol.impl", configValues.get("protocol"));
	}

	public Integer getPort() {
		
		return Integer.parseInt(configValues.get("port"));
	}
	
	public String getHost() {
		
		return configValues.get("host");
	}

}