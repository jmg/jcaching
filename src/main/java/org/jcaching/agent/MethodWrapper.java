package org.jcaching.agent;

import org.apache.commons.configuration.ConfigurationException;
import org.jcaching.JCaching;
import org.jcaching.cache.Cache;
import org.jcaching.exception.ImplementationClassLoadException;

public class MethodWrapper {

	private static final String	PREFIX	= "METHOD_";
	private Cache				cache;
	private Class<?>			c;
	private String				methodName;
	private Object[]			args;
	private int					timeout;

	public MethodWrapper(Object o, String methodName, Object[] args, int timeout) throws ConfigurationException,
			ImplementationClassLoadException {
		
		this.cache = JCaching.getCache();
		this.c = o.getClass();
		this.methodName = methodName;
		this.args = args;
		this.timeout = timeout;
	}

	public Object get() {
		return cache.get(getKey());
	}

	public <T> T set(T value) {
		if (timeout > 0) {
			cache.set(getKey(), value, timeout);
		} else {
			cache.set(getKey(), value);
		}
		return value;
	}

	private String getKey() {
		StringBuilder builder = new StringBuilder();
		builder.append(PREFIX);
		builder.append(c.getName());
		builder.append(".");
		builder.append(methodName);
		builder.append("(");
		for (Object arg : args) {
			builder.append(arg.toString().replace(",", "\\,"));
			builder.append(",");
		}
		return builder.substring(0, builder.length() - 1) + ")";
	}
}
