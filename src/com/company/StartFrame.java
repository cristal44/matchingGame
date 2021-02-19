package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private JPanel startPanel, buttonPanel;
    private JButton startButton;
    private JButton quitButton;
    private JLabel label;

    public StartFrame() {
        initButton();
        setLabel();
        setPanel();
        initFrame();
    }

    private void initFrame() {
        setSize(1000,800);
        setLocationRelativeTo(null);
        setTitle("Memory Matching Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(startPanel);
    }

    private void setPanel() {
        startPanel = new JPanel();

        buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        startPanel.add(label);
        startPanel.add(buttonPanel);
    }

    private void setLabel(){
        label =  new JLabel();

        ImageIcon imageIcon = new ImageIcon("src/images/cover2.jpeg");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(1000,700, Image.SCALE_SMOOTH);

        label.setIcon(new ImageIcon(newImage));
    }

    private void setButtonSize(JButton button){
        button.setSize(500,50);
        button.setPreferredSize(new Dimension(500,50));
    }

    private void initButton() {
        startButton = new JButton("Start");
        setButtonSize(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPatternAndLevel();
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

    private void selectPatternAndLevel() {
        int patternIndex = JOptionPane.showOptionDialog(null ,
                "Choose a Game Mode:",
                "Game Mode",
                JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null
                , Pattern.getPatternList(),
                "default");

        int levelIndex = JOptionPane.showOptionDialog(null ,
                "Choose a Game Level:",
                "Game Level",
                JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null
                ,Level.getLevelList(),
                "default");


        this.setVisible(false);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame gameFrame = new GameFrame(patternIndex, levelIndex);
                gameFrame.setVisible(true);
            }
        });
    }
}

//class ImagePanel extends JPanel{
//    public ImagePanel() {
//        setSize(1000,750);
//        setOpaque(true);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        ImageIcon imageIcon = new ImageIcon("src/images/cover2.jpg");
//        g.drawImage(imageIcon.getImage(), 0,0, this.getWidth(),this.getHeight(),null);
//    }
//}
