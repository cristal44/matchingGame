package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankPanel extends JPanel {
    private Panel labelPanel, tablePanel, buttonPanel;

    public RankPanel(){
        setBackground(new Color(102, 163, 255));
        setOpaque(true);

        setLablePanel();
        setTablePanel();
        setButtonPanel();
    }

    private void setLablePanel(){
        labelPanel = new Panel();
        labelPanel.setPreferredSize(new Dimension(1000,100));
        labelPanel.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Top 10 Ranking List");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("dialog", Font.BOLD, 28));
        labelPanel.add(label);
        add(labelPanel);
    }

    private void setTablePanel(){
        tablePanel = new Panel();
        tablePanel.setPreferredSize(new Dimension(1000,500));


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
        add(tablePanel);
    }

    private void setButtonPanel(){
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(1000,100));

        MenuButton homeButton = new MenuButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removeRankPanel(Frame.start);
            }
        });
        buttonPanel.add(homeButton);

        MenuButton restartButton = new MenuButton("Restart");
        buttonPanel.add(restartButton);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removeRankPanel(Frame.game);
            }
        });

        add(buttonPanel);
    }

}
