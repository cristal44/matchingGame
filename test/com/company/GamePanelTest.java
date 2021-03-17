package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class GamePanelTest {
    @Test
    public void EmptyTest() {
        Pattern p1= Pattern.Disney;
        Level l1 = Level.Easy;
        GamePanel g1 = new GamePanel(p1, l1);

        assertNotNull(g1);
        Pattern p2= Pattern.Animals;
        Level l2 = Level.Medium;
        GamePanel g2 = new GamePanel(p2, l2);
        assertNotNull(g2);

        
    }
}