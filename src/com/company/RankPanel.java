package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankPanel extends JPanel {
    private Level level;

    private static final Dimension LABEL_PANEL_DIMENSION = new Dimension(1000,100);
    private static final Dimension TABLE_PANEL_DIMENSION = new Dimension(1000,500);
    private static final Dimension TABLE_DIMENSION = new Dimension(800,400);
    private static final int LABEL_FONT_SIZE = 28;
    private static final int TABLE_FONT_SIZE = 20;
    private static final int TABLE_ROW_HEIGHT = 40;

    public RankPanel(Level level){
        this.level = level;
        setPanelColor();

        setLabelPanel();
        setTablePanel();
        setButtonPanel();
    }

    private void setPanelColor() {
        setBackground(PanelColor.getPanelColor().getBlue());
        setOpaque(true);
    }

    private void setLabelPanel(){
        Panel labelPanel = new Panel();
        labelPanel.setPreferredSize(LABEL_PANEL_DIMENSION);
        labelPanel.setLayout(new GridBagLayout());

        setLabel(labelPanel);

        add(labelPanel);
    }

    private void setLabel(Panel labelPanel) {
        JLabel label = new JLabel("Top 10 Players (Level: "+ level.toString() +")");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("dialog", Font.BOLD, LABEL_FONT_SIZE));

        labelPanel.add(label);
    }

    private void setTablePanel(){
        Panel tablePanel = new Panel();
//        tablePanel.setPreferredSize(new Dimension(1000,500));
        tablePanel.setPreferredSize(TABLE_PANEL_DIMENSION);

        setTable(tablePanel);
        add(tablePanel);
    }

    private void setTable(Panel tablePanel) {
        String[] columnNames = {"", "", ""};
        String[][] data = PlayerFile.getInstance().getUserList();

        JTable table = new JTable(data,columnNames);
        table.setPreferredSize(TABLE_DIMENSION);
        table.setRowHeight(TABLE_ROW_HEIGHT);
        table.setFont(new Font("dialog", Font.PLAIN,TABLE_FONT_SIZE));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingUtilities.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);

        tablePanel.add(table);
    }

    private void setButtonPanel(){
        Panel buttonPanel = new Panel();
        buttonPanel.setPreferredSize(LABEL_PANEL_DIMENSION);

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
