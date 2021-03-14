package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {
    private static final int SLEEP_TIME = 300;
    private static final int PAIR = 2;

    private int cardNumber;
    private Pattern pattern;
    private GamePanel gamePanel;
    private int totalMatched;

    private List<Card> cards = new ArrayList<>();
    private List<Card> matchCards = new ArrayList<>();

    public CardManager(int cardNumber, Pattern pattern, GamePanel gamePanel) {
        this.cardNumber = cardNumber;
        this.pattern = pattern;
        this.gamePanel = gamePanel;
    }

    public List<Card> generateCards(){
        List<String> patternList = Patterns.getInstance().getPatterns(pattern);
        Collections.shuffle(patternList);
        int number = cardNumber/PAIR;

        for (int i = 0; i < number; i++){
            Card card1 = new Card(i,patternList.get(i));
            Card card2 = new Card(i,patternList.get(i));
            cards.add(card1);
            cards.add(card2);
        }

        Collections.shuffle(cards);

        return  cards;
    }

    public void matchCards(Card a, Card b){
        if (a.getId() == b.getId()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a.setVisible(false);
                    b.setVisible(false);
                    totalMatched += PAIR;

                    if (totalMatched == cardNumber) {
                        GameTimer.getTimerInstance().stop();
                        try {
                            Thread.sleep(SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String name = JOptionPane.showInputDialog(Frame.getInstance(), "Please enter your name:");

                        while (name == null || name.isEmpty()) {
                            name = JOptionPane.showInputDialog(Frame.getInstance(), "Please enter your name:");
                        }
                        PlayerFile.getInstance().add(new User(name, GameTimer.getElapsedTime()));
                        Frame.getInstance().removePanel(gamePanel);
                        Frame.getInstance().addRankPanel();
                    }
                }
            });
        }

    }
    public void addToMatchList(Card card){
        if (matchCards.contains(card)){
            if (!card.isFlipped()) {
                matchCards.remove(card);
            }
            return;
        }

        matchCards.add(card);

        int size = matchCards.size();

        if (size >= PAIR) {
            Card a = matchCards.get(0);
            Card b = matchCards.get(1);

            if (size == PAIR) {
                matchCards(a, b);
            }

            if (size == 3) {
                a.closeCard();
                b.closeCard();
                matchCards.remove(a);
                matchCards.remove(b);
            }
        }
    }

    public void resetCards(){
        totalMatched = 0;
        cards.clear();
        matchCards.clear();
    }
}