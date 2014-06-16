/**
 * FileCacheBackend
 *
 * The file system cache backend implementation.
 */
package org.jcaching.backends.impl.filecachebackend;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.impl.BaseCacheBackend;
import org.jcaching.backends.impl.filecachebackend.storage.MetaObject;

import com.google.gson.Gson;

/**
 * Implements a cache backend using the file system to store the content: files
 * as key containers and content as values.
 */
class FileCacheBackend extends BaseCacheBackend implements CacheBackend {

    /**
     * The configuration key containing the value about the storage path name to
     * use.
     */
    public static final String STORAGE_PATH_KEY = "jcaching.filebackend."
            + "storagePath";

    /**
     * The default value for storage path configuration.
     */
    public static final String STORAGE_PATH_DEFAULT = "./storage";

    /**
     * The timeout value to indicate that indeed timeout is not required.
     */
    public static final int NO_TIMEOUT = 0;

    private String storagePath;

    /**
     * Default constructor.
     *
     * @param configuration The configuration instance.
     */
    public FileCacheBackend(Configuration configuration) {
        super(configuration);
        storagePath = configuration.getString(STORAGE_PATH_KEY,
                STORAGE_PATH_DEFAULT);
    }

    /**
     * {@inheritDoc}
     *
     * Uses the key value as file name to be created on the storage path; the
     * value is used as file content. The key value will be stored indefinitely.
     */
    @Override
    public synchronized void set(String key, Object value) {
        set(key, value, NO_TIMEOUT);
    }

    /**
     * {@inheritDoc}
     *
     * Fetchs the file on storage named as the key value; its content is used as
     * value part. The key value will be invalidated once <code>timeout</code>
     * seconds has passed.
     */
    @Override
    public void set(String key, Object value, int timeout) {
        MetaObject meta = new MetaObject("", timeout); // TODO serialize value to string
        Gson gson = new Gson();
        File f = new File(FilenameUtils.concat(storagePath, key));
        try {
            FileUtils.writeStringToFile(f, gson.toJson(meta));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key) {
        File f = new File(FilenameUtils.concat(storagePath, key));
        Gson gson = new Gson();
        MetaObject meta = null;  // TODO load file content
        gson.fromJson(meta.getValue(), MetaObject.class);
        return null;  // TODO return deserialized object
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String key) {
        FileUtils.deleteQuietly(new File(
            FilenameUtils.concat(storagePath, key)
        ));
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
