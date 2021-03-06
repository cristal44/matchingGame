package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionPanel extends JPanel {
    private Panel backPanel, patternLabelPanel, patternPanel, levelLabelPanel, levelPanel, submitPanel;
    private SelectionButton pokemonButton, fruitButton, animalOverButton, disneyOverButton;
    private SelectionButton easyButton, mediumButton, hardButton;
    private MenuButton submitButton;

    private Pattern pattern = null;
    private Level level = null;

    public SelectionPanel(){
        setBackground(new Color(102, 163, 255));
        setOpaque(true);
        setLayout(new FlowLayout());

        setBackPanel();
        patternLabelPanel();
        setPatternPanel();
        levelLabelPanel();
        setLevelPanel();
        setSubmitPanel();
    }



    private void setBackPanel() {
        backPanel = new Panel();
        backPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        backPanel.setPreferredSize(new Dimension(1000,100));

        MenuButton backButton = new MenuButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().removeSelectionPanel(SelectionPanel.this);
            }
        });

        backPanel.add(backButton);
        add(backPanel);
    }

    private void setSubmitPanel() {
        submitPanel = new Panel();

        submitPanel.setBorder(BorderFactory.createEmptyBorder(180, 0, 0, 0));
        submitPanel.setPreferredSize(new Dimension(1000,300));


        submitButton = new MenuButton("Start Game");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pattern == null || level == null ) {
                    JOptionPane.showMessageDialog(null,
                            "You need to choose a pattern and a level to start the game");
                } else {
                    Frame.getInstance().removeSelectionPanel(SelectionPanel.this, pattern, level);
                }
            }
        });

        submitButton.setPreferredSize(new Dimension(500,50));


        submitPanel.add(submitButton);
        add(submitPanel);

    }

    private void levelLabelPanel() {
        levelLabelPanel = new Panel();
        levelLabelPanel.setPreferredSize(new Dimension(1000,80));
        levelLabelPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel label = new JLabel("Choose a Game Level:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("dialog", Font.BOLD, 28));

        levelLabelPanel.add(label);
        add(levelLabelPanel);
    }

    private void setLevelPanel() {
        levelPanel = new Panel();
        levelPanel.setPreferredSize(new Dimension(1000,100));

        setLevelBotton();
        levelPanel.add(easyButton);
        levelPanel.add(mediumButton);
        levelPanel.add(hardButton);
        add(levelPanel);
    }


    private void setSelectedLevelButton(SelectionButton selectedBtn, SelectionButton[] notSelectionBtnList){
        selectedBtn.setOpaque(true);
        selectedBtn.setForeground(Color.BLUE);

        for (int i = 0; i < notSelectionBtnList.length; i++){
            setButtonColorBlack(notSelectionBtnList[i]);
        }
    }

    private void setButtonColorBlack(SelectionButton button){
        button.setOpaque(true);
        button.setForeground(Color.BLACK);
    }


    private void setLevelBotton() {
        easyButton = new SelectionButton("Easy");
        setButtonColorBlack(easyButton);
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Level.Easy;

                setSelectedLevelButton(easyButton, new SelectionButton[]{mediumButton,hardButton});
            }
        });

        mediumButton = new SelectionButton("Medium");
        setButtonColorBlack(mediumButton);
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Level.Medium;
                setSelectedLevelButton(mediumButton,new SelectionButton[]{hardButton, easyButton});
            }
        });

        hardButton = new SelectionButton("Hard");
        setButtonColorBlack(hardButton);
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Level.Hard;
                setSelectedLevelButton(hardButton, new SelectionButton[]{easyButton, mediumButton});
            }
        });
    }

    private void patternLabelPanel() {
        patternLabelPanel = new Panel();
        patternLabelPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        patternLabelPanel.setPreferredSize(new Dimension(1000,80));

        JLabel label = new JLabel("Choose a Game Pattern:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("dialog", Font.BOLD, 28));

        patternLabelPanel.add(label);
        add(patternLabelPanel);
    }


    private void setPatternPanel() {
        patternPanel = new Panel();
        patternPanel.setPreferredSize(new Dimension(1000,100));

        setPatternButtons();
        patternPanel.add(pokemonButton);
        patternPanel.add(fruitButton);
        patternPanel.add(animalOverButton);
        patternPanel.add(disneyOverButton);
        add(patternPanel);
    }

    private void setPatternButtons() {
        pokemonButton = new SelectionButton("Pokemon");
        pokemonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Pokemon;
                setSelectedLevelButton(pokemonButton,
                        new SelectionButton[]{fruitButton, animalOverButton,disneyOverButton});
            }
        });


        fruitButton = new SelectionButton("Fruit");
        fruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Fruit;
                setSelectedLevelButton(fruitButton,
                        new SelectionButton[]{pokemonButton, animalOverButton,disneyOverButton});
            }
        });

        animalOverButton = new SelectionButton("Animal");
        animalOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Animals;
                setSelectedLevelButton(animalOverButton,
                        new SelectionButton[]{pokemonButton, fruitButton,disneyOverButton});
            }
        });


        disneyOverButton = new SelectionButton("Disney");
        disneyOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Disney;
                setSelectedLevelButton(disneyOverButton,
                        new SelectionButton[]{pokemonButton, animalOverButton,fruitButton});
            }
        });
    }
}
