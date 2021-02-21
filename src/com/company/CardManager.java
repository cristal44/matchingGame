package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {
    private int cardNumber;
    private Pattern pattern;
    private static CardManager cardManager = null;
    private static int totalMatched = 0;
    private static GameFrame gameFrame;

    private List<Card> cards = new ArrayList<>();
    private List<Card> matchCards = new ArrayList<>();

    private String[] patternList = {
            "src/images/p1.png",
            "src/images/p2.png",
            "src/images/p3.png",
            "src/images/p4.png",
            "src/images/p5.jpeg",
            "src/images/a1.jpg",
            "src/images/a2.jpg",
            "src/images/a3.png",
            "src/images/a4.png",
    };

    public static CardManager getInstance(){
        return cardManager;
    }

    public static void init(int cardNumber, Pattern pattern, GameFrame frame) {
        gameFrame = frame;
        cardManager = new CardManager(cardNumber, pattern);
    }

    private CardManager(int cardNumber, Pattern pattern) {
        this.cardNumber = cardNumber;
        this.pattern = pattern;
    }

    public List<Card> generateCards(){
        int number = cardNumber/2;

        for (int i = 0; i < number; i++){
            Card card1 = new Card(i,patternList[i]);
            Card card2 = new Card(i,patternList[i]);
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
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a.setVisible(false);
                    a.setMatched(true);
                    b.setVisible(false);
                    b.setMatched(true);

                    totalMatched += 2;

                    if (totalMatched == cardNumber) {
                        GameTimer.getTimerInstance().stop();
                        System.out.println("NICE JOB");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String name = JOptionPane.showInputDialog(gameFrame, "Please enter your name:");
                        System.out.println(name);
                        if (name != null) {
                            Utils.getInstance().add(new User(name, GameTimer.getElapsedTime()));
                            gameFrame.addRankPanel();
                            gameFrame.removeGamePanel();


                        }
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

        if (size >= 2) {
            Card a = matchCards.get(0);
            Card b = matchCards.get(1);

            if (size == 2) {
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