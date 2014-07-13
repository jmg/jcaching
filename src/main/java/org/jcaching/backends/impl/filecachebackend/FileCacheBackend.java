/**
 * FileCacheBackend
 *
 * The file system cache backend implementation.
 */

package org.jcaching.backends.impl.filecachebackend;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jcaching.backends.CacheBackend;
import org.jcaching.backends.impl.BaseCacheBackend;
import org.jcaching.backends.impl.filecachebackend.storage.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Implements a cache backend using the file system to store the content: files
 * as key containers and content as values.
 */
public class FileCacheBackend extends BaseCacheBackend implements CacheBackend {

    /**
     * The configuration key containing the value about the storage path name to
     * use.
     */
    public static final String STORAGE_PATH_KEY = "jcaching.filebackend."
            + "storagePath";

    /**
     * The default value for storage path configuration.
     */
    public static final String STORAGE_PATH_DEFAULT = "storage";

    /**
     * The timeout value to indicate that indeed timeout is not required.
     */
    public static final int NO_TIMEOUT = 0;

    private static final Logger logger = LoggerFactory
            .getLogger(FileCacheBackend.class);

    private String storagePath;

    /**
     * Default constructor.
     *
     * @param configuration The configuration instance.
     */
    public FileCacheBackend(Configuration configuration) {
        super(configuration);
        initialize();
    }

    /**
     * {@inheritDoc}
     *
     * <p>Other tasks performed:<p>
     *
     * <ul>
     *   <li>Creation of storage directory.</li>
     * </ul>
     *
     * @see CacheBackend#initialize()
     */
    public void initialize() {
        super.initialize();

        storagePath = configuration.getString(STORAGE_PATH_KEY,
                STORAGE_PATH_DEFAULT);

        File f = new File(storagePath);
        if (!f.exists()) {
            logger.info("Creating storage directory: {}", storagePath);
            f.mkdirs(); // Creates multiple subdirectory levels if required
        }
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
        logger.debug("Key={}, value={}, class={}, timeout={}", key, value,
                value.getClass().getCanonicalName(), timeout);

        Gson gson = new Gson();
        MetaObject meta = new MetaObject(gson.toJson(value), value.getClass()
                .getCanonicalName(), timeout);

        File f = new File(FilenameUtils.concat(storagePath, key));

        try {
            FileUtils.writeStringToFile(f, gson.toJson(meta));
        } catch (IOException e) {
            logger.error("Exception trying to write the key {} on storage {}:",
                    key, storagePath, e);
        }

        logger.debug("Key {} value was saved on storage.", key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key) {
        String path = FilenameUtils.concat(storagePath, key);
        logger.debug("Fetching data for key={} in path={}", key, storagePath);

        File f = new File(path);
        List<String> lines = null;

        try {
            lines = FileUtils.readLines(f, "UTF-8");
        } catch (IOException e) {
            logger.info("Key file {} cannot be open/found in storage path {}:",
                    key, storagePath, e);
            return null;
        }

        Joiner joiner = Joiner.on(" ").skipNulls();
        Gson gson = new Gson();

        MetaObject meta = gson.fromJson(joiner.join(lines), MetaObject.class);
        logger.debug("Meta object fetched: {}", meta);

        if (meta.isInvalid()) {
            logger.info("Key content is invalid due to timeout.");
            return null;
        }

        logger.debug("Deserializing object into class {}", meta.getClassName());

        try {
            return gson.fromJson(meta.getValue(),
                    Class.forName(meta.getClassName()));
        } catch (JsonSyntaxException | ClassNotFoundException e) {
            logger.error("Exception trying to deserialize saved instance:", e);
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String key) {
        File f = new File(FilenameUtils.concat(storagePath, key));
        
        if (!f.exists()) {
            logger.info(
                "Key {} does not exists on storage; nothing to do.", key
            );
        }
        
        f.delete();
        logger.info("Removed key {} from storage.", key);
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
