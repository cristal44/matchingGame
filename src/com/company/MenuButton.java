package com.company;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    public MenuButton(String name) {
        setText(name);

        setBackground(Color.BLUE);
        setup();
    }

    public void setup(){
       setSize(100,50);
       setPreferredSize(new Dimension(100,50));
    }
}
