package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameFrame extends JFrame {
    private Pattern pattern;
    private Level level;
    private MenuButton homeButton, pauseButton, startOverButton;
    private GamePanel mainPanel, menuPanel,cardPanel, timerPanel;
    private GamePanel rankPanel, labelPanel, tablePanel, buttonPanel;
    private JLabel timeLabel;
    private int cardRow = 0;
    private int cardCols = 0;

    public GameFrame(int patternIndex, int levelIndex) {
        this.pattern =Pattern.getPattern(patternIndex);
        this.level =Level.getLevel(levelIndex);


        Utils.getInstance().read();
        initialMenuButtons();

        setupFrame();
//        addRankPanel();

        addGamePanel();
    }

    public void addRankPanel(){
        rankPanel = new GamePanel();

        labelPanel = new GamePanel();
        labelPanel.setPreferredSize(new Dimension(1000,100));
        labelPanel.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Top 10 Ranking List");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("dialog", Font.BOLD, 28));
        labelPanel.add(label);


        tablePanel = new GamePanel();
        tablePanel.setPreferredSize(new Dimension(1000,500));


        String[] columnNames = {"", "", ""};
        String[][] data = Utils.getInstance().getUserList();

        JTable table = new JTable(data,columnNames);
        table.setPreferredSize(new Dimension(800,400));
        table.setRowHeight(40);
        table.setFont(new Font("dialog", Font.PLAIN,20));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingUtilities.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);

        tablePanel.add(table);



        buttonPanel = new GamePanel();
        buttonPanel.add(homeButton);
        MenuButton restartButton = new MenuButton("Restart");
        buttonPanel.add(restartButton);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRankPanel();
                addGamePanel();
                setGameRestart();
            }
        });

        buttonPanel.setPreferredSize(new Dimension(1000,100));


        rankPanel.add(labelPanel);
        rankPanel.add(tablePanel);
        rankPanel.add(buttonPanel);

        setContentPane(rankPanel);

    }

    public void addGamePanel(){
        setCardLevel();
        setGamePanels();
        GameTimer.init(timeLabel);
        setCards();
    }

    public void removeGamePanel(){
        getContentPane().remove(cardPanel);
        getContentPane().remove(menuPanel);
        getContentPane().remove(timerPanel);
        getContentPane().remove(mainPanel);
        repaint();
        getContentPane().revalidate();
    }

    public void removeRankPanel(){
        getContentPane().remove(rankPanel);
        repaint();
        getContentPane().revalidate();
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
