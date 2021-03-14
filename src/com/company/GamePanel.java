package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private Panel cardPanel;
    private int cardRow;
    private int cardCols;
    private Pattern pattern;
    private Level level;
    private CardManager cardManager;

//    private static final Color MY_BLUE = new Color(102, 163, 255);
    private static final Dimension TIMER_PANEL_DIMENSION = new Dimension(110,60);
    private static final Dimension TIMER_LABEL_DIMENSION = new Dimension(100,50);
    private static final int TIME_LABEL_SIZE = 14;
    private static final int GAP = 10;
    private static final int TOP = 30;

//    private static final int COLS = 4;
//    private static final int HARD_COLS = 6;
//    private static final int ROWS = 3;
//    private static final int EASY_ROWS = 2;
    private static final int BORDER = 0;


    public GamePanel(Pattern pattern, Level level) {
        this.pattern = pattern;
        this.level = level;

        setBackground(PanelColor.getPanelColor().getBlue());
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
        menuPanel.setBorder(BorderFactory.createEmptyBorder(GAP, BORDER, BORDER, BORDER));

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
                Frame.getInstance().addSelectionPanel();
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
        timerPanel.setPreferredSize(TIMER_PANEL_DIMENSION);
        timerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        add(timerPanel);
    }

    private void setTimeLabel(Panel timerPanel) {
        JLabel timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("dialog", Font.BOLD, TIME_LABEL_SIZE));
        timeLabel.setPreferredSize(TIMER_LABEL_DIMENSION);
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timerPanel.add(timeLabel);

        GameTimer.init(timeLabel);
        GameTimer.reset();
    }


    private void setCardLevel(){
        switch (level){
//            case Easy:
//                cardRow = EASY_ROWS;
//                cardCols = COLS;
//                break;
//            case Medium:
//                cardRow = ROWS;
//                cardCols = COLS;
//                break;
//            case Hard:
//                cardRow = ROWS;
//                cardCols = HARD_COLS;

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
        cardPanel.setLayout(new GridLayout(cardRow, cardCols,GAP,GAP));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(TOP, BORDER, BORDER, BORDER));
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
