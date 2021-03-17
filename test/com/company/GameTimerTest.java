package com.company;

import static org.junit.Assert.assertNotNull;

import java.util.Timer;

import javax.swing.JLabel;

import org.junit.Test;


public class GameTimerTest {
    @Test
    public void EmptyTest() {
        JLabel j1 = new JLabel();
        GameTimer.init(j1);
        assertNotNull(this);

    }
}
