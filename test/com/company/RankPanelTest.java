package com.company;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class RankPanelTest {
    @Test
    public void EmptyTest() { 
        RankPanel rp1 = new RankPanel(Level.Easy);
        assertNotNull(rp1);

        RankPanel rp2 = new RankPanel(Level.Medium);
        assertNotNull(rp2);
        RankPanel rp3 = new RankPanel(Level.Hard);
        assertNotNull(rp3);
    }
}