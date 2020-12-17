package com.example.service.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "abc".getBytes();
        byte[] actual = "abcd".getBytes();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testAssertEquals() {
        Assert.assertEquals("two objects are not equal", "abc", "abcd");
    }

    @Test
    public void testAssertFalse() {
        boolean condition = true;
        Assert.assertFalse("should be false,but is true", condition);
    }

    @Test
    public void testAssertTrue() {
        boolean condition = true;
        Assert.assertTrue("should be false,but is true", condition);
    }

    @Test
    public void testAssertNotNull() {
        Assert.assertNotNull("should not be null", new Object());
    }

    @Test
    public void testAssertNotSame() {
        Assert.assertNotSame("should not be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertNull() {
        Assert.assertNull("should be null", new AssertTest());
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(123);
        Integer bNumber = aNumber;
        Assert.assertSame("should be same", aNumber, bNumber);
    }

    @Test
    public void testAssertThat() {
        //MatcherAssert.assertThat();
    }
}
