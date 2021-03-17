package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void emptyTest(){
        Card c1 = new Card(456, "");
        assertNotNull(c1);

        
        Card c2 = new Card(Integer.MAX_VALUE, "");
        assertNotNull(c2);

        assertNotEquals(c1,c2);
    }


    @Test
    public void idTest() { 

        Card c1 = new Card(456, "");
        assertEquals(c1.getId(), 456);

        Card c2 = new Card(Integer.MAX_VALUE, "");
        assertEquals(c2.getId(), Integer.MAX_VALUE);


    }
    @Test
    public void flippedTest(){
        Card c1 = new Card(456, "");
        assertFalse(c1.isFlipped());

        
        Card c2 = new Card(Integer.MAX_VALUE, "");
        assertFalse(c1.isFlipped());

        c1.onClick();
        assertTrue(c1.isFlipped());
        c2.onClick();
        assertTrue(c2.isFlipped());

    }


}