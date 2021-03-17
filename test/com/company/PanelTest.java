package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.awt.Color;

public class PanelTest{
    @Test
    public void EmptyTest() { 
        Panel p1 = new Panel();
        assertNotNull(p1);
    }
    @Test
    public void colorTest(){
        Panel p1 = new Panel();
        Color c1 = new Color(102,163,255);
        assertEquals(c1, p1.getBackground());
    }
}