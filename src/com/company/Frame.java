package com.company;

import javax.swing.*;

public class Frame extends JFrame {
    public static final String start = "STARTFRAME";
    public static final String rank = "RANKFRAME";
    public static final String game = "GAMEFRAME";

    private static Frame frame = null;
    private JPanel gamePanels;
    private JPanel rankPanels;

    private int pattern;
    private int level;

    public static Frame getInstance(){
        if (frame==null) {
            frame = new Frame();
        }
        return frame;
    }

    private Frame(){
        setSize(1000,800);
        setTitle("Memory Matching Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void removeStartPanel(int pattern, int level){
        this.pattern = pattern;
        this.level = level;
        removePanel(StartPanel.getInstance());
        gamePanels = new GamePanel(pattern,level);
        addPanel(gamePanels);
    }

    public void removeGamePanel(String panelName) {
        removePanel(gamePanels);

        if (panelName.equals(start) ) {
            addPanel(StartPanel.getInstance());
        }

        if (panelName.equals(rank) ) {
            rankPanels = new RankPanel();
            addPanel(rankPanels);
        }
    }

    public void removeRankPanel(String panelName){
        removePanel(rankPanels);

        if (panelName.equals(start) ) {
            addPanel(StartPanel.getInstance());
        }

        if (panelName.equals(game) ) {
            gamePanels = new GamePanel(pattern,level);
            addPanel(gamePanels);
        }
    }


    private void removePanel(JPanel panel){
        getContentPane().remove(panel);
        repaint();
        getContentPane().revalidate();
    }

    public void addPanel(JPanel panel){
        getContentPane().add(panel);
        setVisible(true);
    }
}
