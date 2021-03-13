package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private Panel cardPanel;
    private int cardRow = 0;
    private int cardCols = 0;
    private Pattern pattern;
    private Level level;
    private CardManager cardManager;

    public GamePanel(Pattern pattern, Level level) {
        this.pattern = pattern;
        this.level = level;

        setBackground(new Color(102, 163, 255));
        setOpaque(true);

        PlayerFile.getInstance().read(level);
        setCardLevel();

        setMenuPanel();
        setTimePanel();
        setCardPanel();

        setCards();
    }

    private void setMenuPanel() {
        Panel menuPanel = new Panel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        setHomeButton(menuPanel);
        setPauseButton(menuPanel);
        setStartOverButton(menuPanel);

        add(menuPanel);
    }


    private void setHomeButton(Panel menuPanel){
        MenuButton homeButton = new MenuButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removePanel(GamePanel.this);
            }
        });
        menuPanel.add(homeButton);
    }

    private void setPauseButton(Panel menuPanel){
        MenuButton pauseButton = new MenuButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameTimer.getTimerInstance().stop();
            }
        });
        menuPanel.add(pauseButton);
    }


    private void setStartOverButton(Panel menuPanel) {
        MenuButton startOverButton = new MenuButton("Start Over");
        startOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameRestart();
            }
        });
        menuPanel.add(startOverButton);
    }


    private void setGameRestart(){
        GameTimer.getTimerInstance().stop();
        GameTimer.reset();

        cardManager.resetCards();
        remove(cardPanel);
        repaint();
        revalidate();

        setCardPanel();
        setCards();
    }


    private void setTimePanel(){
        Panel timerPanel = new Panel();

        setTimeLabel(timerPanel);
        timerPanel.setPreferredSize(new Dimension(110,60));
        timerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        add(timerPanel);
    }

    private void setTimeLabel(Panel timerPanel) {
        JLabel timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("dialog", Font.BOLD, 14));
        timeLabel.setPreferredSize(new Dimension(100, 50));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timerPanel.add(timeLabel);

        GameTimer.init(timeLabel);
        GameTimer.reset();
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

    public void setCardPanel(){
        cardPanel = new Panel();
        cardPanel.setLayout(new GridLayout(cardRow, cardCols,10,10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        add(cardPanel);
    }


    public void setCards(){
        cardManager = new CardManager(cardRow*cardCols, pattern, this);

        java.util.List<Card> cardList = cardManager.generateCards();

        for (Card card : cardList) {
            cardPanel.add(card);
            card.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card.onClick();
                    cardManager.addToMatchList(card);
                    GameTimer.getTimerInstance().start();
                }
            });
        }
    }
}
