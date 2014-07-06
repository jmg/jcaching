package org.jcaching.config;

public @interface Cacheable {
	int timeout() default 0;
}
