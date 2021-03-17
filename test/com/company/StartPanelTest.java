package com.company;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class StartPanelTest{
    @Test
    public void EmptyTest() { 
        StartPanel sp1 = new StartPanel();
        assertNotNull(sp1);
    }
}