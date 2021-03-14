package com.company;

import javax.swing.*;
import java.awt.*;

public class Card extends JButton {
    private int id;
    private String coverIcon = "src/images/orange.jpg";
    private String backIcon = "";
    private boolean isFlipped = false;

    public Card(int id, String backIcon){
        this.id = id;
        this.backIcon = backIcon;

        setSize(150, 200);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.white, 2));
        setCardImage(coverIcon);
    }

    public void setCardImage(String icon){
        ImageIcon imageIcon = new ImageIcon(icon);
        Image image = imageIcon.getImage().getScaledInstance(150,200,Image.SCALE_SMOOTH);

        ImageIcon cardImageIcon = new ImageIcon(image);
        setIcon(cardImageIcon);
    }

    public int getId() {
        return id;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void closeCard() {
        setCardImage(coverIcon);
        isFlipped = false;
    }

    public void onClick() {
        if (isFlipped) {
            setCardImage(coverIcon);
            isFlipped = false;

        } else {
            isFlipped = true;
            setCardImage(backIcon);
        }
    }
}
