/**
 * CacheBackend
 *
 * TODO Description if available.
 */

package org.jcaching.backends;

/**
 * TODO description
 */
public interface CacheBackend {
    /**
     * Set a serialized object in cache using a key and returns true in case of
     * success.
     *
     * @param key TODO
     * @param serializedObj TODO
     * @return TODO
     */
    boolean set(String key, String serializedObj);

    /**
     * Set a serialized object in cache using a key and returns true in case of
     * success.
     *
     * @param key TODO
     * @param serializedObj TODO
     * @param timeout TODO
     * @return TODO
     */
    boolean set(String key, String serializedObj, int timeout);
    
    /**
     * Returns the serialized object from cache.
     *
     * @param key TODO
     * @return TODO
     */
    String get(String key);
    
    /**
     * Delete a serialized object from cache using a key.
     *
     * @param key TODO
     * @return TODO
     */
    boolean delete(String key);
}
