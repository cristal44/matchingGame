package com.company;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest{
    @Test
    public void nameTimeTest() { 
        User u1 = new User("Jane",123);
        assertEquals(u1.getName(), "Jane"); 
        assertEquals(u1.getTime(), 123); 

        User u2 = new User(" ",0);
        assertEquals(u2.getName(), " "); 
        assertEquals(u2.getTime(), 0); 

        User u3 = new User("SDDSFSDFDFGD123456%^%$#",Integer.MAX_VALUE);
        assertEquals(u3.getName(), "SDDSFSDFDFGD123456%^%$#"); 
        assertEquals(u3.getTime(), Integer.MAX_VALUE); 
    }

    
}