package com.company;

import javax.swing.*;
import java.awt.*;

public class Card extends JButton {
    private static final int THICKNESS = 2;
    private static final int CARD_WIDTH = 150;
    private static final int CARD_HEIGHT = 200;

    private int id;
    private String coverIcon = "src/images/orange.jpg";
    private String backIcon = "";
    private boolean isFlipped = false;

    public Card(int id, String backIcon){
        this.id = id;
        this.backIcon = backIcon;

        setSize(CARD_WIDTH, CARD_HEIGHT);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.white, THICKNESS));
        setCardImage(coverIcon);
    }

    public void setCardImage(String icon){
        ImageIcon imageIcon = new ImageIcon(icon);
        Image image = imageIcon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT,Image.SCALE_SMOOTH);

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
