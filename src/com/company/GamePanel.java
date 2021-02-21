package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private Panel menuPanel,cardPanel, timerPanel;
    private MenuButton homeButton, pauseButton, startOverButton;
    private JLabel timeLabel;
    private int cardRow = 0;
    private int cardCols = 0;
    private Pattern pattern;
    private Level level;

    public GamePanel(int patternIndex, int levelIndex) {
        setBackground(new Color(102, 163, 255));
        setOpaque(true);

        this.pattern =Patterns.getInstance().getPattern(patternIndex);
        this.level =Level.getLevel(levelIndex);

        PlayerFile.getInstance().read();
        setCardLevel();

        setMenuPanel();
        setTimePanel();
        setCardPanel();

        setCards();
    }

    private void setMenuPanel() {
        menuPanel = new Panel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        setButtons();
        menuPanel.add(homeButton);
        menuPanel.add(pauseButton);
        menuPanel.add(startOverButton);
        add(menuPanel);
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

    private void setTimePanel(){
        timerPanel = new Panel();

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("dialog", Font.BOLD, 14));
        timeLabel.setPreferredSize(new Dimension(100, 50));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timerPanel.add(timeLabel);
        timerPanel.setPreferredSize(new Dimension(110,60));
        timerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));


        GameTimer.init(timeLabel);
        GameTimer.reset();

        add(timerPanel);
    }

    private void setButtons() {
        homeButton = new MenuButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removeGamePanel(Frame.start);
            }
        });

        pauseButton = new MenuButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameTimer.getTimerInstance().stop();
            }
        });

        startOverButton = new MenuButton("Start Over");
        startOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameRestart();
            }
        });
    }

    private void setGameRestart(){
        GameTimer.getTimerInstance().stop();
        System.out.println("GameTimer.getTimerInstance().stop();");
        GameTimer.reset();

        CardManager.getInstance().resetCards();
        remove(cardPanel);
        repaint();
        revalidate();

        setCardPanel();
        setCards();
    }

    public void setCards(){
        CardManager.init(cardRow*cardCols, pattern);
        CardManager.getInstance().resetCards();

        java.util.List<Card> cardList = CardManager.getInstance().generateCards();

        for (Card card : cardList) {
            cardPanel.add(card);
            card.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card.onClick();
                    GameTimer.getTimerInstance().start();
                }
            });
        }
    }
}
