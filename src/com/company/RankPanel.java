package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankPanel extends JPanel {
    private Level level;

    public RankPanel(Level level){
        this.level = level;
        setPanelColor();

        setLabelPanel();
        setTablePanel();
        setButtonPanel();
    }

    private void setPanelColor() {
        setBackground(new Color(102, 163, 255));
        setOpaque(true);
    }

    private void setLabelPanel(){
        Panel labelPanel = new Panel();
        labelPanel.setPreferredSize(new Dimension(1000,100));
        labelPanel.setLayout(new GridBagLayout());

        setLabel(labelPanel);

        add(labelPanel);
    }

    private void setLabel(Panel labelPanel) {
        JLabel label = new JLabel("Top 10 Players (Level: "+ level.toString() +")");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("dialog", Font.BOLD, 28));

        labelPanel.add(label);
    }

    private void setTablePanel(){
        Panel tablePanel = new Panel();
        tablePanel.setPreferredSize(new Dimension(1000,500));

        setTable(tablePanel);
        add(tablePanel);
    }

    private void setTable(Panel tablePanel) {
        String[] columnNames = {"", "", ""};
        String[][] data = PlayerFile.getInstance().getUserList();

        JTable table = new JTable(data,columnNames);
        table.setPreferredSize(new Dimension(800,400));
        table.setRowHeight(40);
        table.setFont(new Font("dialog", Font.PLAIN,20));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingUtilities.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);

        tablePanel.add(table);
    }

    private void setButtonPanel(){
        Panel buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(1000,100));

        setHomeButton(buttonPanel);
        setRestartButton(buttonPanel);

        add(buttonPanel);
    }

    private void setHomeButton(Panel buttonPanel){
        MenuButton homeButton = new MenuButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removePanel(RankPanel.this);
                Frame.getInstance().addSelectionPanel();
            }
        });

        buttonPanel.add(homeButton);
    }

    private void setRestartButton(Panel buttonPanel){
        MenuButton restartButton = new MenuButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removePanel(RankPanel.this);
                Frame.getInstance().addGamePanel();
            }
        });

        buttonPanel.add(restartButton);
    }


}
