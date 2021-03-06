package com.company;

import javax.swing.*;

public class Frame extends JFrame {
    public static final String selection = "SELEXCTIONFRAME";
    public static final String rank = "RANKFRAME";
    public static final String game = "GAMEFRAME";

    private static Frame frame = null;
    private JPanel gamePanels;
    private JPanel rankPanels;

    public static Pattern pattern;
    public static Level level;

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

    public void removeStartPanel(){
        removePanel(StartPanel.getInstance());
        addPanel(new SelectionPanel());
    }

    public void removeGamePanel(String panelName) {
        removePanel(gamePanels);

        if (panelName.equals(selection) ) {
            addPanel(new SelectionPanel());
        }

        if (panelName.equals(rank) ) {
            rankPanels = new RankPanel();
            addPanel(rankPanels);
        }
    }

    public void removeRankPanel(String panelName){
        removePanel(rankPanels);

        if (panelName.equals(selection) ) {
            addPanel(new SelectionPanel());
        }

        if (panelName.equals(game) ) {
            gamePanels = new GamePanel();
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

    public void removeSelectionPanel(SelectionPanel selectionPanel, Pattern pattern, Level level) {
        this.pattern = pattern;
        this.level = level;
        removePanel(selectionPanel);

        gamePanels = new GamePanel();
        addPanel(gamePanels);
    }

    public void removeSelectionPanel(SelectionPanel selectionPanel) {
        removePanel(selectionPanel);
        addPanel(StartPanel.getInstance());
    }
}
