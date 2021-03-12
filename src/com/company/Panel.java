package com.company;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        setBackgroundColor();
    }

    private void setBackgroundColor() {
        setBackground(new Color(102, 163, 255));
        setOpaque(true);
    }
}
