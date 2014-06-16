/**
 * MetaObjectTestCase
 *
 * Test cases for MetaObject class.
 */

package org.jcaching.backends.impl.filecachebackend.storage;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Corroborates the behaviour for MetaObject method that verifies the
 * valid data condition.
 */
@RunWith(Parameterized.class)
public class MetaObjectTestCase {

    private String value;

    private int timeout;

    private Calendar calendar;

    private boolean expected;

    /**
     * Default constructor.
     *
     * @param value TODO
     * @param timeout TODO
     * @param calendar TODO
     * @param expected TODO
     */
    public MetaObjectTestCase(String value, int timeout, Calendar calendar,
            boolean expected) {

        this.value = value;
        this.timeout = timeout;
        this.calendar = calendar;
        this.expected = expected;
    }

    /**
     * @return The initialization values with expected test result.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
            { "hello world!", 100, Calendar.getInstance(), false },
            { "hollo world!", 1, new GregorianCalendar(2014,1,31), true },
        });
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Asserts that method result for isInvalid() method is equals to an
     * expected one.
     */
    @Test
    public void testIsInvalid() {
        MetaObject mo = new MetaObject(value, timeout, calendar);
        Assert.assertEquals(expected, mo.isInvalid());
    }
}

// vim:ft=java ts=4 tw=80 cc=+1: 
