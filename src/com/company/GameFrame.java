package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private Frame frame;
    private String pattern;
    private String level;
    private MenuButton homeButton, playButton, pauseButton, startOverButton;
    private GamePanel mainPanel, menuPanel,cardPanel;

    public GameFrame(String pattern, String level) {
        this.pattern = pattern;
        this.level = level;

        initialMenuButtons();
        setPanels();
        setCards();
        setupFrame();
    }

    private void setupFrame() {
        setSize(1000,800);
        setTitle("Memory Matching Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(mainPanel);
    }

    private void backToStartFrame(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartFrame startFrame = new StartFrame();
                startFrame.setVisible(true);
            }
        });

        this.setVisible(false);
    }
    private void initialMenuButtons() {
        homeButton = new MenuButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToStartFrame();
            }
        });

        playButton = new MenuButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        pauseButton = new MenuButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        startOverButton = new MenuButton("Start Over");
        startOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setPanels(){
        mainPanel = new GamePanel();

        menuPanel = new GamePanel();
        menuPanel.setPreferredSize(new Dimension(1000,100));
        menuPanel.add(homeButton);
        menuPanel.add(playButton);
        menuPanel.add(pauseButton);
        menuPanel.add(startOverButton);

        cardPanel = new GamePanel();
        cardPanel.setPreferredSize(new Dimension(500,350));
        cardPanel.setLayout(new GridLayout(2,4,10,10));

        mainPanel.add(menuPanel);
        mainPanel.add(cardPanel);
    }

    public void setCards(){
        for (int i = 0; i < 8; i++) {
            JButton button = new JButton();
            ImageIcon imageIcon = new ImageIcon("src/images/orange.jpg");
            int offset = button.getInsets().left;
            button.setIcon(resizeIcon(imageIcon, button.getWidth()-offset, button.getHeight()-offset));
            button.setBorder(BorderFactory.createLineBorder(Color.white, 2));

            cardPanel.add(button);
        }
    }


    private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
