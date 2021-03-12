package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private static StartPanel startPanel = null;

    public static StartPanel getInstance(){
        if (startPanel == null) {
            startPanel = new StartPanel();
        }
        return startPanel;
    }

    private StartPanel() {
        setLabel();
        setButtonPanel();
    }

    private void setLabel(){
        JLabel label =  new JLabel();

        ImageIcon imageIcon = new ImageIcon("src/images/cover2.jpeg");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(1000,700, Image.SCALE_SMOOTH);

        label.setIcon(new ImageIcon(newImage));

        add(label);
    }

    private void setButtonPanel(){
        JPanel buttonPanel = new JPanel();

        setStartButton(buttonPanel);
        setQuitButton(buttonPanel);

        add(buttonPanel);
    }

    private void setStartButton(JPanel buttonPanel){
        JButton startButton = new JButton("Start");
        setButtonSize(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removeStartPanel();
            }
        });

        buttonPanel.add(startButton);
    }


    private void setQuitButton(JPanel buttonPanel) {
        JButton quitButton = new JButton("Quit");
        setButtonSize(quitButton);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(quitButton);
    }

    private void setButtonSize(JButton button){
        button.setSize(500,50);
        button.setPreferredSize(new Dimension(500,50));
    }
}
