package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


public class PlayerFileTest{
    @Test
    public void EmptyTest() { 
        PlayerFile pf1 = PlayerFile.getInstance();
        assertNotNull(pf1);
        PlayerFile pf2 = PlayerFile.getInstance();
        assertEquals(pf1,pf2);
    }

    // @Test
    // public void userListTest(){
    //     PlayerFile pf1 = PlayerFile.getInstance();
    //     User u1 = new User("John", 100);
    //     pf1.add(u1);
    //     assertEquals(1, pf1.getUserList().length);


    // }
}