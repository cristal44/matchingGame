package com.company;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        setBackgroundColor();
    }

    private void setBackgroundColor() {
        setBackground(PanelColor.getPanelColor().getBlue());
        setOpaque(true);
    }
}
