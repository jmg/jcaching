/**
 * FileCacheBackendTestCase
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.filecachebackend;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * TODO description
 */
public class FileCacheBackendTestCase {

    private FileCacheBackend backend;

    private PropertiesConfiguration configuration;

    /**
     * Default constructor.
     */
    public FileCacheBackendTestCase() {
    }

    @Before
    public void setUp() throws ConfigurationException {
        configuration = new PropertiesConfiguration("jcaching.properties");
        configuration.setProperty(
            FileCacheBackend.STORAGE_PATH_KEY, "/tmp/storage"
        );
        configuration.save();
        backend = new FileCacheBackend(configuration);
    }

    @After
    public void tearDown() {
        throw new NotImplementedException();
    }

    /**
     * TODO
     */
    @Test
    public void testSet() {
        throw new NotImplementedException();
    }

    /**
     * TODO
     */
    @Test
    public void testGet() {
        throw new NotImplementedException();
    }

    /**
     * <p>Validates the delete cache method for a given key. The procedure
     * is:</p>
     * <ul>
     *   <li>Verify that key file does not exist.</li>
     *   <li>Set the key and verify existence.</li>
     *   <li>Delete the key and verify absence.</li>
     * </ul>
     */
    @Test
    public void testDelete() {
        String key = "ariel";

        File f = new File(FilenameUtils.concat(
            configuration.getString(FileCacheBackend.STORAGE_PATH_KEY), key
        ));
        Assert.assertTrue(!f.exists());

        backend.set(key, "leira");
        Assert.assertTrue(f.exists() && !f.isDirectory());

        backend.delete(key);
        Assert.assertNull(backend.get(key));
        Assert.assertTrue(!f.exists());
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
