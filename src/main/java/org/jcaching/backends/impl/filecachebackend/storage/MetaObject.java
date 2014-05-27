/**
 * MetaObject
 *
 * TODO Description if available.
 */

package org.jcaching.backends.impl.filecachebackend.storage;

import java.util.Date;

/**
 * TODO
 */
public class MetaObject {

    private int timeout;

    private String value;

    private Date date = new Date();

    /**
     * Constructor.
     *
     * @param value The string-serialized object as value to store.
     * @param timeout The timeout value in seconds.
     */
    public MetaObject(String value, int timeout) {
        this.value = value;
        this.timeout = timeout;
    }

    /**
     * @return The timeout value for key content invalidation.
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @return The string-serialized object value to store.
     */
    public String getValue() {
        return value;
    }

    /**
     * @return The meta object creation date.
     */
    public Date getDate() {
        return date;
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
