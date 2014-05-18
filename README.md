jcaching
========

What is it?
-----------

**jcaching** is a simple Java caching framework implementing a variety of
backends solutions.

This is a assignature project being built for assignarture "Algoritmos II" in
Universidad Tecnológica Nacional - Facultad Regional Buenos Aires.

How can I compile it?
---------------------

TODO compilation steps

How can I use it?
-----------------

Simple get from cache. If the value is not there just set it. The second time the value will be read from cache.

```java
package examples;

import org.apache.commons.configuration.ConfigurationException;
import org.jcaching.JCaching;
import org.jcaching.cache.Cache;
import org.jcaching.exception.ImplementationClassLoadException;

public class Simple {
	
	public static void main(String[] args) throws ConfigurationException,
            ImplementationClassLoadException {
		
		System.out.println(getFromCache());
	}
	
	public static String getFromCache() {
		
		Cache cache = JCaching.getCache();
		String value = (String) cache.get("key-1");
		if (value == null) {
			/* The value assosiated with key-1 wasn't in cache. 
			   Set the value within the key for the next time. */
			value = "my value";
			cache.set("key-1", value);
			System.out.println("Set " + value + " on cache!");
		} else {
			/* The value assosiated with key-1 was in cache. Bingo! */
			System.out.println("Read " + value + " from cache!");
		}
		return value;
	}
}

```

Contributors
------------

* [Juan Manuel García](https://github.com/jmg)
* [Mauro Szuchman](https://github.com/mszuchman)
* [Ariel Gerardo Ríos](https://github.com/ariel17)


