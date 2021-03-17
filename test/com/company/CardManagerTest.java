package com.company;
import com.company.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;




public class CardManagerTest {
    @Test
    public void EmptyTest() { 
        Pattern p1= Pattern.Disney;
        Level l1 = Level.Easy;
        GamePanel g1 = new GamePanel(p1, l1);
        CardManager cm1 = new CardManager(10, p1, g1);

        Pattern p2= Pattern.Animals;
        Level l2 = Level.Medium;
        GamePanel g2 = new GamePanel(p2, l2);
        CardManager cm2 = new CardManager(20, p2, g2);

        Pattern p3= Pattern.Fruit;
        Level l3 = Level.Hard;
        GamePanel g3 = new GamePanel(p3, l3);
        CardManager cm3 = new CardManager(30, p3, g3);

        Pattern p4= Pattern.Pokemon;
        Level l4 = Level.Hard;
        GamePanel g4 = new GamePanel(p4, l4);
        CardManager cm4 = new CardManager(100, p4, g4);

        assertNotNull(cm1);
        assertNotNull(cm2);
        assertNotNull(cm3);
        assertNotNull(cm4);
    }

    @Test
    public void numberOfCardsTest(){
        Pattern p1= Pattern.Disney;
        Level l1 = Level.Easy;
        GamePanel g1 = new GamePanel(p1, l1);
        CardManager cm1 = new CardManager(10, p1, g1);
        assertEquals(cm1.getCardNumber(), 10);
        Pattern p2= Pattern.Animals;
        Level l2 = Level.Medium;
        GamePanel g2 = new GamePanel(p2, l2);
        CardManager cm2 = new CardManager(20, p2, g2);
        assertEquals(cm2.getCardNumber(), 20);

        Pattern p3= Pattern.Fruit;
        Level l3 = Level.Hard;
        GamePanel g3 = new GamePanel(p3, l3);
        CardManager cm3 = new CardManager(30, p3, g3);
        assertEquals(cm3.getCardNumber(), 30);

        Pattern p4= Pattern.Pokemon;
        Level l4 = Level.Hard;
        GamePanel g4 = new GamePanel(p4, l4);
        CardManager cm4 = new CardManager(100, p4, g4);
        assertEquals(cm4.getCardNumber(), 100);
    }

    @Test
    public void clearCardsTest(){

        Pattern p1= Pattern.Disney;
        Level l1 = Level.Easy;
        GamePanel g1 = new GamePanel(p1, l1);
        CardManager cm1 = new CardManager(10, p1, g1);
        cm1.generateCards();
        int prevSize = cm1.getCards().size();
        assertEquals(0, 10-prevSize);

    }

    // @Test
    // public void matchingTest(){
    //     Pattern p1= Pattern.Disney;
    //     Level l1 = Level.Easy;
    //     GamePanel g1 = new GamePanel(p1, l1);
    //     CardManager cm1 = new CardManager(10, p1, g1);
    //     cm1.generateCards();
    //     int prev = cm1.getTotalMatched();
    //     Card c1 = cm1.getCards().get(0);
    //     Card c2 = null;
    //     for (int i = 1; i < cm1.getCards().size(); i++) {
    //         if (cm1.getCards().get(i).getId() == c1.getId()){
    //             c2 = cm1.getCards().get(i);
    //         }
    //     }
    //     cm1.matchCards(c1, c2);
        
    //     int cur = cm1.getTotalMatched();
    //     assertEquals(prev + 2, cur);
        


    // }

}