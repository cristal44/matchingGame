package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private Pattern pattern;
    private Level level;
    private MenuButton homeButton, playButton, pauseButton, startOverButton;
    private GamePanel mainPanel, menuPanel,cardPanel;
    private int cardRow = 0;
    private int cardCols = 0;

    public GameFrame(int patternIndex, int levelIndex) {
        this.pattern =Pattern.getPattern(patternIndex);
        this.level =Level.getLevel(levelIndex);

        setCardLevel();
        initialMenuButtons();
        setPanels();
        setCards();
        setupFrame();
    }

    private void setCardLevel(){
        switch (level){
            case Easy:
                cardRow = 2;
                cardCols = 4;
                break;
            case Medium:
                cardRow = 3;
                cardCols = 4;
                break;
            case Hard:
                cardRow = 3;
                cardCols = 6;
        }
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
        cardPanel.setLayout(new GridLayout(cardRow, cardCols,10,10));

        mainPanel.add(menuPanel);
        mainPanel.add(cardPanel);
    }

    public void setCards(){
        for (int i = 0; i < cardRow * cardCols; i++) {
            JButton button = new JButton();
            button.setSize(150, 200);
            button.setOpaque(true);
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
