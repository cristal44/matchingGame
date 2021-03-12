package com.company;

import javax.swing.*;
import java.awt.*;

public class SelectionButton extends JButton {
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
        setSize(150,80);
        setPreferredSize(new Dimension(120,60));
    }
}
