/**
 * Simple
 *
 * TODO Description if available.
 */

package org.jcaching.examples;

import org.apache.commons.configuration.ConfigurationException;
import org.jcaching.JCaching;
import org.jcaching.cache.Cache;
import org.jcaching.exception.ImplementationClassLoadException;

/**
 *
 *
 * @author
 */
public class Simple {

    public static void main(String[] args) throws ConfigurationException,
            ImplementationClassLoadException {

        System.out.println(">> Simple example for JCaching use:");
        
        Cache cache = JCaching.getInstance();
        
        System.out.print("Checking key is empty... ");
        
        String key = "key-1";
        assert cache.get(key) == null;

        System.out.println("Done :)");

        System.out.print("Setting new value on cache... ");

        String value = "my value";
        cache.set(key, value);

        System.out.println("Done :)");

        System.out.print("Checking key value in cache... ");

        String cacheValue = (String) cache.get(key);
        assert cacheValue != null;
        assert cacheValue.equals(value);

        System.out.println("Done :)");

        System.out.println(">> Success");
    }
}
