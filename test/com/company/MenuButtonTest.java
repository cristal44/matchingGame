package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.*;

public class MenuButtonTest {
    @Test
    public void EmptyTest() { 
        MenuButton mb1 = new MenuButton("start");
        //Dimension d1 = new Dimension(100,50);
        assertNotNull(mb1);
        MenuButton mb2 = new MenuButton("end");
        assertNotNull(mb2);
    }

    @Test 
    public void sizeTest(){
        MenuButton mb1 = new MenuButton("start");

        assertEquals(100, mb1.getWidth());
        assertEquals(50, mb1.getHeight());

        MenuButton mb2 = new MenuButton("back");

        assertEquals(100, mb2.getWidth());
        assertEquals(50, mb2.getHeight());
    }
}