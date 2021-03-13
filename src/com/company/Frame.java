package com.company;

import javax.swing.*;

public class Frame extends JFrame {
    private static Frame frame = null;
    private JPanel removedPanel;
    private Boolean isFromCardManager = false;
    private Boolean isRestartGame = false;
    private Pattern pattern = null;
    private Level level = null;

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

    public void setFromCardManager(Boolean fromCardManager) {
        isFromCardManager = fromCardManager;
    }

    public void setRestartGame(Boolean restartGame) {
        isRestartGame = restartGame;
    }

    public void setGamePatternAndLevel(Pattern pattern, Level level){
        this.pattern = pattern;
        this.level = level;
    }


    public void removePanel(JPanel panel){
        getContentPane().remove(panel);
        repaint();
        getContentPane().revalidate();

        removedPanel = panel;
        addPanel();
    }

    public void addPanel(JPanel panel){
        getContentPane().add(panel);
        setVisible(true);
    }

    public void addPanel(){
        if (removedPanel != null) {
            if (removedPanel.getClass() == StartPanel.class) {
                addPanel(new SelectionPanel());
            }

            if (removedPanel.getClass() == SelectionPanel.class) {
                if (pattern == null && level == null){
                    addPanel(StartPanel.getInstance());
                } else {
                    addPanel(new GamePanel(pattern,level));
                }
            }

            if (removedPanel.getClass() == GamePanel.class){
                if (isFromCardManager){
                    addPanel(new RankPanel(level));
                    isFromCardManager = false;
                } else {
                    addPanel(new SelectionPanel());
                }
            }

            if (removedPanel.getClass() == RankPanel.class){
                if (isRestartGame){
                    addPanel(new GamePanel(pattern,level));
                    isRestartGame = false;
                } else {
                    addPanel(new SelectionPanel());
                }
            }
        }
    }
}
