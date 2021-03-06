package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private JPanel buttonPanel;
    private JLabel label;
    private JButton startButton, quitButton;
    private static StartPanel startPanel = null;

    public static StartPanel getInstance(){
        if (startPanel == null) {
            startPanel = new StartPanel();
        }
        return startPanel;
    }

    private StartPanel() {
        setLabel();
        setButton();

        buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        add(label);
        add(buttonPanel);
    }

    private void setLabel(){
        label =  new JLabel();

        ImageIcon imageIcon = new ImageIcon("src/images/cover2.jpeg");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(1000,700, Image.SCALE_SMOOTH);

        label.setIcon(new ImageIcon(newImage));
    }


    private void setButton() {
        startButton = new JButton("Start");
        setButtonSize(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removeStartPanel();


//                selectPatternAndLevel();
            }
        });

        quitButton = new JButton("Quit");
        setButtonSize(quitButton);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void setButtonSize(JButton button){
        button.setSize(500,50);
        button.setPreferredSize(new Dimension(500,50));
    }


//    private void selectPatternAndLevel() {
//        int patternIndex = JOptionPane.showOptionDialog(null ,
//                "Choose a Game Mode:",
//                "Game Mode",
//                JOptionPane.NO_OPTION,
//                JOptionPane.PLAIN_MESSAGE,
//                null
//                , Patterns.getInstance().getPatternList(),
//                "default");
//
//        if (patternIndex < 0){
//            return;
//        }
//
//        int levelIndex = JOptionPane.showOptionDialog(null ,
//                "Choose a Game Level:",
//                "Game Level",
//                JOptionPane.NO_OPTION,
//                JOptionPane.PLAIN_MESSAGE,
//                null
//                ,Level.getLevelList(),
//                "default");
//
//        if (levelIndex < 0){
//            return;
//        }
//
//        Frame.getInstance().removeStartPanel(patternIndex, levelIndex);
//    }

}
