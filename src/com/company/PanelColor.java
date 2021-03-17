package com.company;

import java.awt.*;

public class PanelColor {
    private static final Color MY_BLUE = new Color(102, 163, 255);
    private static PanelColor panelColor = null;

    public static PanelColor getPanelColor(){
        if (panelColor == null){
            panelColor = new PanelColor();
        }
        return panelColor;
    }

    public Color getBlue(){
        return MY_BLUE;
    }
}
