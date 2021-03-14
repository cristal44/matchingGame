package com.company;

import javax.swing.*;
import java.awt.*;

public class SelectionButton extends JButton {
    private static final Dimension DIMENSION = new Dimension(150,80);
    private static final Dimension PRE_DIMENSION = new Dimension(120,60);

    public SelectionButton(String name) {
        setText(name);
        setRequestColor();
        setRequestSize();
    }

    private void setRequestColor() {
        setOpaque(true);
        setForeground(Color.BLACK);
    }

    private void setRequestSize() {
        setSize(DIMENSION);
        setPreferredSize(PRE_DIMENSION);
    }
}
