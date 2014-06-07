package org.jcaching.storage.impl.memorystorage;

import java.util.Date;

import org.jcaching.storage.StorageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimedObject implements StorageObject {

	private String value;
	private Integer timeout;
	private Long cachedAt;
	private Logger logger;

	public TimedObject(String value, Integer timeout) {
	
		this.value = value;
		this.timeout = timeout;
		this.cachedAt = getSecondsNow();
		this.logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	private Long getSecondsNow() {
		return new Date().getTime() / 1000;
	}
	
	@Override
	public boolean expired() {
		
		if (this.timeout == null) {
			logger.debug("object has not timeout");
			return false;
		}
		
		Long now = getSecondsNow();
		
		logger.debug("Object cached + timeout {}", this.cachedAt + this.timeout);
		logger.debug("Datetime now {}", now);
		
		return this.cachedAt + this.timeout < now; 		
	}

}
