/**
 * Dummy
 *
 * A "complex" dummy class for testing.
 */

package org.jcaching.backends.impl.filecachebackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * TODO description
 */
public class Dummy {

    /**
     * Logger instance fo Dummy class.
     */
    private static final Logger logger = LoggerFactory.getLogger(Dummy.class);

    private String foo;

    private int bar;

    /**
     * Dummy class constructor.
     *
     * @param foo Dummy parameter 1.
     * @param bar Dummy parameter 2.
     */
    public Dummy(String foo, int bar) {
        this.foo = foo;
        this.bar = bar;
    }

    /**
     * @return The foo value.
     */
    public String getFoo() {
        return foo;
    }

    /**
     * @return The bar value.
     */
    public int getBar() {
        return bar;
    }

    /**
     * {@inheritDoc}
     * @see Object#equals(Object)
     */
    public boolean equals(Object other) {
        if (other == null) {
            logger.debug("Other object is null.");
            return false;
        }

        if (getClass() != other.getClass()) {
            logger.debug("Other class missmatch.");
            return false;
        }

        if (!foo.equals(((Dummy) other).foo)) {
            logger.debug("Foo value missmatch.");
            return false;
        }

        if (bar != ((Dummy) other).bar) {
            logger.debug("Bar value missmatch.");
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     * @see Object#toString()
     */
    public String toString() {
        return String.format("Dummy<foo='%s' bar=%d>", foo, bar);
    }
}
