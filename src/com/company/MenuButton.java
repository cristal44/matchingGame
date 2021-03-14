package com.company;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    private Dimension dimension = new Dimension(100,50);

    public MenuButton(String name) {
        setText(name);
        setup();
    }

    public void setup(){
        setSize(dimension);
        setPreferredSize(new Dimension(dimension));
    }
}
