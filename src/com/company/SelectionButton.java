package com.company;

import javax.swing.*;
import java.awt.*;

public class SelectionButton extends JButton {
    public SelectionButton(String name) {
        setText(name);

        setOpaque(true);
        setForeground(Color.BLACK);
        setSize(150,80);
        setPreferredSize(new Dimension(120,60));
    }

}
