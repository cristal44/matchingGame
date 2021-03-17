package com.company;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class FrameTest {
    @Test
    public void EmptyTest() { 
        Frame f1 = Frame.getInstance();
        assertNotNull(f1);
    }

}