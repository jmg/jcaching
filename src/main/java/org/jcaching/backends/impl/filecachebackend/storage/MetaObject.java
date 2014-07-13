/**
 * MetaObject
 *
 * A object wrapper to store related meta data to the target instance to be
 * cached.
 */

package org.jcaching.backends.impl.filecachebackend.storage;

import java.util.Calendar;

/**
 * Stores related meta data to the instance to be cached and the serialized
 * instance itself.
 */
public class MetaObject {

    static public final int NO_TIMEOUT = 0;

    private int timeout;

    private String value;

    private Calendar calendar;

    private String className;

    /**
     * Constructor.
     *
     * @param value The string-serialized object as value to store.
     * @param className
     */
    public MetaObject(String value, String className) {
        this(value, className, 0, Calendar.getInstance());
    }

    /**
     * Constructor.
     *
     * @param value The string-serialized object as value to store.
     * @param className
     * @param timeout The timeout value in seconds.
     */
    public MetaObject(String value, String className, int timeout) {
        this(value, className, timeout, Calendar.getInstance());
    }

    /**
     * Constructor.
     *
     * @param value The string-serialized object as value to store.
     * @param className
     * @param timeout The timeout value in seconds.
     * @param calendar The current date and time value.
     */
    public MetaObject(String value, String className, int timeout,
            Calendar calendar) {
        this.value = value;
        this.className = className;
        this.timeout = timeout;
        this.calendar = calendar;
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
     * @return The meta object creation date and time.
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * @return The class name for the serialized instance.
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return The boolean value about status for the current cache. If
     * <code>true</code> the data store was invalidated due to cache timeout;
     * if result is <code>false</code> the data is still valid on cache storage,
     * due to infinite timeout or timeout not yet reached.
     */
    public boolean isInvalid() {
        if (timeout == NO_TIMEOUT) {
            return false;
        }

        Calendar temp = (Calendar) calendar.clone();
        temp.add(Calendar.SECOND, timeout);
        return Calendar.getInstance().getTime().getTime() >
            temp.getTime().getTime();
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
