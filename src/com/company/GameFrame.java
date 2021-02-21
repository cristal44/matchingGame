package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private Pattern pattern;
    private Level level;
    private MenuButton homeButton, playButton, pauseButton, startOverButton;
    private GamePanel mainPanel, menuPanel,cardPanel, timerPanel;
//    private JPanel timerPanel;
    private JLabel timeLabel;
    private int cardRow = 0;
    private int cardCols = 0;

    public GameFrame(int patternIndex, int levelIndex) {
        this.pattern =Pattern.getPattern(patternIndex);
        this.level =Level.getLevel(levelIndex);


        Utils.getInstance().read();
        initialMenuButtons();

        setupFrame();

        addGamePanel();
    }

    public void addGamePanel(){
        setCardLevel();
        setGamePanels();
        GameTimer.init(timeLabel);
        setCards();
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

//                try{
//                    Thread.sleep(500);
//                } catch (Exception exception){
//                    exception.getStackTrace();
//
//                }
//
                setGameRestart();


            }
        });
    }

    private void setGameRestart(){

        GameTimer.getTimerInstance().stop();
        System.out.println("GameTimer.getTimerInstance().stop();");
        GameTimer.reset();

        // clear cards list
        CardManager.getInstance().resetCards();


        // remove cardPanel from GameFrame
        getContentPane().remove(cardPanel);
//        getContentPane().remove(menuPanel);
//        getContentPane().remove(timerPanel);
//        getContentPane().remove(mainPanel);
        repaint();
        getContentPane().revalidate();


        // adding an empty cardPanel
        setCardPanel();
        setCards();
//        addGamePanel();

    }


    public void setGamePanels(){
        mainPanel = new GamePanel();

        menuPanel = new GamePanel();
        menuPanel.setPreferredSize(new Dimension(800,100));
        menuPanel.add(homeButton);
        menuPanel.add(pauseButton);
        menuPanel.add(startOverButton);

        timerPanel = new GamePanel();
        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("dialog", Font.BOLD, 14));
        timeLabel.setPreferredSize(new Dimension(100, 50));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerPanel.add(timeLabel);
        timerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        mainPanel.add(menuPanel);
        mainPanel.add(timerPanel);
        setCardPanel();

        setContentPane(mainPanel);
    }

    public void setCardPanel(){
        cardPanel = new GamePanel();
        cardPanel.setLayout(new GridLayout(cardRow, cardCols,10,10));
        mainPanel.add(cardPanel);
    }

    public void setCards(){
        CardManager.init(cardRow*cardCols, pattern,this);
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
