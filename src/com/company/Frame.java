package com.company;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static final Dimension FRAME_SIZE = new Dimension(1000,800);

    private static Frame frame = null;
    private Pattern pattern;
    private Level level;

    public static Frame getInstance(){
        if (frame==null) {
            frame = new Frame();
        }
        return frame;
    }

    private Frame(){
        setSize(FRAME_SIZE);
        setTitle("Memory Matching Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setGamePatternAndLevel(Pattern pattern, Level level){
        this.pattern = pattern;
        this.level = level;
    }

    public void removePanel(JPanel panel){
        getContentPane().remove(panel);
        repaint();
        getContentPane().revalidate();
    }

    private void addPanel(JPanel panel){
        getContentPane().add(panel);
        setVisible(true);
    }

    public void addStartPanel(){
        addPanel(new StartPanel());
    }

    public void addSelectionPanel(){
        addPanel(new SelectionPanel());
    }

    public void addGamePanel(){
        addPanel(new GamePanel(pattern,level));
    }

    public void addRankPanel(){
        addPanel(new RankPanel(level));
    }
}
