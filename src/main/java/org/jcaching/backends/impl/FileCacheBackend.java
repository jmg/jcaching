/**
 * FileCacheBackend
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl;

import org.jcaching.backends.CacheBackend;

/**
 * TODO
 */
class FileCacheBackend implements CacheBackend {

    /**
     * Default constructor.
     */
    public FileCacheBackend() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(String key, String serializedObj) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(String key, String serializedObj, int timeout) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String key) {
        // TODO Auto-generated method stub
        return false;
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
