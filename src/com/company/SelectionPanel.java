package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionPanel extends JPanel {
    private SelectionButton pokemonButton, fruitButton, animalButton, disneyButton;
    private SelectionButton easyButton, mediumButton, hardButton;

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
        Panel backPanel = new Panel();
        backPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        backPanel.setPreferredSize(new Dimension(1000,100));

        setbackButton(backPanel);
        add(backPanel);
    }

    private void setbackButton(Panel backPanel) {
        MenuButton backButton = new MenuButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.getInstance().setGamePatternAndLevel(null,null);
                Frame.getInstance().removePanel(SelectionPanel.this);
            }
        });

        backPanel.add(backButton);
    }


    private void levelLabelPanel() {
        Panel levelLabelPanel = new Panel();
        levelLabelPanel.setPreferredSize(new Dimension(1000,80));
        levelLabelPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        setLabel(levelLabelPanel, "Choose a Game Level:");

        add(levelLabelPanel);
    }

    private void setLabel(Panel labelPanel, String message) {
        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("dialog", Font.BOLD, 28));

        labelPanel.add(label);
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

    private void patternLabelPanel() {
        Panel patternLabelPanel = new Panel();
        patternLabelPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        patternLabelPanel.setPreferredSize(new Dimension(1000,80));

        setLabel(patternLabelPanel, "Choose a Game Pattern:");

        add(patternLabelPanel);
    }


    private void setPatternPanel() {
        Panel patternPanel = new Panel();
        patternPanel.setPreferredSize(new Dimension(1000,100));

        add(patternPanel);

        setPokemonButton(patternPanel);
        setFruitButton(patternPanel);
        setAnimalButton(patternPanel);
        setDisneyButton(patternPanel);

    }

    private void setPokemonButton(Panel patternPanel){
        pokemonButton = new SelectionButton("Pokemon");
        patternPanel.add(pokemonButton);

        pokemonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Pokemon;
                setSelectedLevelButton(pokemonButton,
                        new SelectionButton[]{fruitButton, animalButton,disneyButton});
            }
        });
    }

    private void setFruitButton(Panel patternPanel){
        fruitButton = new SelectionButton("Fruit");
        patternPanel.add(fruitButton);

        fruitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Fruit;
                setSelectedLevelButton(fruitButton,
                        new SelectionButton[]{pokemonButton, animalButton,disneyButton});
            }
        });


    }

    private void setAnimalButton(Panel patternPanel){
        animalButton = new SelectionButton("Animal");
        patternPanel.add(animalButton);


        animalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Animals;
                setSelectedLevelButton(animalButton,
                        new SelectionButton[]{pokemonButton, fruitButton,disneyButton});
            }
        });

    }

    private void setDisneyButton(Panel patternPanel){
        disneyButton = new SelectionButton("Disney");
        patternPanel.add(disneyButton);

        disneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pattern = Pattern.Disney;
                setSelectedLevelButton(disneyButton,
                        new SelectionButton[]{pokemonButton, animalButton,fruitButton});
            }
        });

    }

    private void setLevelPanel() {
        Panel levelPanel = new Panel();
        levelPanel.setPreferredSize(new Dimension(1000,100));

        setEasyButton(levelPanel);
        setMediumButton(levelPanel);
        setHardButton(levelPanel);

        add(levelPanel);
    }

    private void setEasyButton(Panel levelPanel) {
        easyButton = new SelectionButton("Easy");
        setButtonColorBlack(easyButton);

        levelPanel.add(easyButton);

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Level.Easy;

                setSelectedLevelButton(easyButton, new SelectionButton[]{mediumButton,hardButton});
            }
        });

    }

    private void setMediumButton(Panel levelPanel) {
        mediumButton = new SelectionButton("Medium");
        setButtonColorBlack(mediumButton);

        levelPanel.add(mediumButton);

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Level.Medium;
                setSelectedLevelButton(mediumButton,new SelectionButton[]{hardButton, easyButton});
            }
        });
    }



    private void setHardButton(Panel levelPanel) {
        hardButton = new SelectionButton("Hard");
        setButtonColorBlack(hardButton);

        levelPanel.add(hardButton);

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Level.Hard;
                setSelectedLevelButton(hardButton, new SelectionButton[]{easyButton, mediumButton});
            }
        });

    }


    private void setSubmitPanel() {
        Panel submitPanel = new Panel();

        submitPanel.setBorder(BorderFactory.createEmptyBorder(180, 0, 0, 0));
        submitPanel.setPreferredSize(new Dimension(1000,300));

        setSubmitButton(submitPanel);

        add(submitPanel);
    }

    private void setSubmitButton(Panel submitPanel) {
        MenuButton submitButton = new MenuButton("Start Game");
        submitButton.setPreferredSize(new Dimension(500,50));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pattern == null || level == null ) {
                    JOptionPane.showMessageDialog(null,
                            "You need to choose a pattern and a level to start the game");
                } else {
//                    Frame.getInstance().removeSelectionPanel(SelectionPanel.this, pattern, level);
//                    Frame.getInstance().removeSelectionPanel(SelectionPanel.this);
//                    Frame.getInstance().addGamePanel(pattern, level);
                    Frame.getInstance().setGamePatternAndLevel(pattern,level);
                    Frame.getInstance().removePanel(SelectionPanel.this);
                }
            }
        });

        submitPanel.add(submitButton);
    }
}
