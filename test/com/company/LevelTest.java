package com.company;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class LevelTest{
    @Test
    public void EmptyTest() {
        Level l1 = Level.Easy;
        assertNotNull(l1);
        Level l2 = Level.Medium;
        assertNotNull(l2);
        Level l3 = Level.Hard;
        assertNotNull(l3);
    }
}