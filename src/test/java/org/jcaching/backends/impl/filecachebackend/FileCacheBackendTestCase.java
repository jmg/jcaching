/**
 * FileCacheBackendTestCase
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.filecachebackend;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO description
 */
public class FileCacheBackendTestCase {

    /**
     * The logger instance for this test class.
     */
    private static final Logger logger = LoggerFactory.getLogger(
        FileCacheBackendTestCase.class
    );

    private FileCacheBackend backend;

    private PropertiesConfiguration configuration;

    private String key;

    private String value;

    private Dummy dummy;

    /**
     * Default constructor.
     */
    public FileCacheBackendTestCase() {
    }

    @Before
    public void setUp() throws ConfigurationException {
        configuration = new PropertiesConfiguration("jcaching.properties");
        configuration.setProperty(FileCacheBackend.STORAGE_PATH_KEY,
                "/tmp/jcaching/storage");
        configuration.save();
        backend = new FileCacheBackend(configuration);
        key = UUID.randomUUID().toString();
        value = "ariel";
        dummy = new Dummy("Ariel", 17);
    }

    @After
    public void tearDown() throws IOException {
        FileUtils.deleteDirectory(new File(configuration.getString(
            FileCacheBackend.STORAGE_PATH_KEY
        )));
    }

    /**
     * TODO
     */
    @Test
    public void testSetAndGet() {
        File f = new File(FilenameUtils.concat(
            configuration.getString(FileCacheBackend.STORAGE_PATH_KEY), key
        ));

        Assert.assertFalse(
            "The key path should not exist", f.exists()
        );

        backend.set(key, value);
        Assert.assertTrue(
            "The key path now should exist", f.exists() && !f.isDirectory()
        );

        String result = (String) backend.get(key);
        Assert.assertNotNull("The get() method should not return null", result);
        Assert.assertEquals(
            "The get() method result and value used on set() method should " +
            "be equals", value, result
        );
    }

    /**
     * TODO
     */
    @Test
    public void testGetNullKey() {
        File f = new File(FilenameUtils.concat(
            configuration.getString(FileCacheBackend.STORAGE_PATH_KEY), key
        ));
        Assert.assertFalse(
            "The key path should not exist", f.exists()
        );

        Assert.assertNull(
            "The get() operation over a non-existing key should return null",
            backend.get(key)
        );
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
    public void testSetAndDelete() {
        File f = new File(FilenameUtils.concat(
            configuration.getString(FileCacheBackend.STORAGE_PATH_KEY), key
        ));
        Assert.assertFalse(
            "The file for key in storage directory should not exist",
            f.exists()
        );

        backend.set(key, value);
        Assert.assertTrue(
            "Path should exists and not as directory",
            f.exists() && !f.isDirectory()
        );

        backend.delete(key);
        Assert.assertNull(
            "The get() method on a deleted key should return null",
            backend.get(key)
        );
        Assert.assertTrue(
            "The path should not exists after a delete() method", !f.exists()
        );
    }

    @Test
    public void testSetAndGetComplexClass() {
        key = "dummy";

        File f = new File(FilenameUtils.concat(
            configuration.getString(FileCacheBackend.STORAGE_PATH_KEY), key
        ));

        Assert.assertFalse(
            "The file for key in storage directory should not exist",
            f.exists()
        );

        backend.set(key, dummy);

        Assert.assertTrue(
            "Path should exists and not as directory",
            f.exists() && !f.isDirectory()
        );

        Dummy result = (Dummy) backend.get(key);
        logger.debug("Fetched dummy instance: {}", result);

        Assert.assertNotNull("The get() method should not return null", result);
        Assert.assertTrue(
            "The get() method result and value used on set() method should " +
            "be equals", dummy.equals(result)
        );
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
