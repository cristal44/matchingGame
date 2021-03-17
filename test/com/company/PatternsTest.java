package com.company;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

public class PatternsTest{
    @Test
    public void EmptyTest(){
        Patterns ps1 = Patterns.getInstance();
        assertNotNull(ps1);
    }


    @Test
    public void patternListTest() { 
        Patterns ps1 = Patterns.getInstance();
        Pattern p1 = Pattern.Animals;
        Pattern p2 = Pattern.Disney;
        Pattern p3 = Pattern.Fruit;
        Pattern p4 = Pattern.Pokemon;
        
        List<String> l1 = ps1.getPatterns(p1);
        List<String> l2 = ps1.getPatterns(p2);
        List<String> l3 = ps1.getPatterns(p3);
        List<String> l4 = ps1.getPatterns(p4);

        assertEquals(9, l1.size());
        assertEquals(9, l2.size());
        assertEquals(9, l3.size());
        assertEquals(9, l4.size());

    }

}