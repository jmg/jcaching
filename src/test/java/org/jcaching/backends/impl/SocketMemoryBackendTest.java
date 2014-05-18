/**
 * SocketMemoryBackendTest
 *
 * Test units for MemorySocketBackend behaviour.
 */

package org.jcaching.backends.impl;

import junit.framework.TestCase;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jcaching.JCaching;
import org.jcaching.backends.impl.socketmemorybackend.server.CacheDeamon;
import org.jcaching.cache.Cache;
import org.jcaching.exception.ImplementationClassLoadException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO description
 */
public class SocketMemoryBackendTest {

    private static Configuration configuration;

    private static CacheDeamon deamon;

    private Cache cache;

    @BeforeClass
    public static void setUpClass() throws ImplementationClassLoadException {
        configuration = new PropertiesConfiguration();

        configuration.setProperty("jcaching.backend.className",
                "org.jcaching.backends.impl.socketmemorybackend." +
                        "SocketMemoryBackend");

        configuration.setProperty("jcaching.defaultTimeout", "10");

        configuration.setProperty("jcaching.backends.socketmemorybackend.host",
                "localhost");

        configuration.setProperty("jcaching.backends.socketmemorybackend.port",
                "22122");

        configuration.setProperty(
                "jcaching.backends.socketmemorybackend.protocol",
                "org.jcaching.backends.impl.socketmemorybackend.protocol." +
                    "impl.SimpleProtocol");

        deamon = new CacheDeamon(configuration);

        deamon.serverForeverInAThread();
    }

    @Before
    public void setUp() throws ConfigurationException,
            ImplementationClassLoadException {

        JCaching.setConfiguration(configuration);
        cache = JCaching.getCache();
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
        if (deamon != null) {
            deamon.stopServer();
        }
    }    
    
    @Test
    public void testCacheGet() {
        
        cache.set("key-1", "test value");
        TestCase.assertEquals("test value", cache.get("key-1")
        );
    }
    
    @Test
    public void testCacheDelete() {
        
        cache.set("key-1", "test");
        cache.delete("key-1");

        TestCase.assertEquals(null, cache.get("key-1"));
    }
    
    @Test
    public void testCacheGetObject() {
    	
    	Person person = new Person("juan", 25);
    	cache.set("person-1", person);
    	
    	Person cachedPerson = (Person) cache.get("person-1");
        TestCase.assertEquals(person.getName(), cachedPerson.getName());
        TestCase.assertEquals(person.getAge(), cachedPerson.getAge());
    }
}
