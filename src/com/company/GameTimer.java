package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer{
    private static Timer timer;
    private static int hour = 0;
    private static int minute = 0;
    private static int second = 0;
    private static int elapsedTime = 0;

    private static JLabel label;

    public static Timer getTimerInstance(){
        return timer;
    }

    public static void init (JLabel label){
        new GameTimer(label);
    }

    private GameTimer(JLabel label) {
        this.label = label;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime = elapsedTime + 1000;
                hour = (elapsedTime/3600000);
                minute = (elapsedTime/60000) % 60;
                second = (elapsedTime/1000) % 60;

                label.setText(String.format("%02d:%02d:%02d",hour,minute,second));
            }
        });
    }

    public static void reset(){
        hour = 0;
        minute = 0;
        second = 0;
        elapsedTime = 0;
        label.setText(String.format("%02d:%02d:%02d",hour,minute,second));
    }

    public static int getElapsedTime() {
        return elapsedTime;
    }

    public static String convertToTimeString(int elapsedTime){
        int h = (elapsedTime/3600000);
        int m = (elapsedTime/60000) % 60;
        int s = (elapsedTime/1000) % 60;

        return String.format("%02d:%02d:%02d",h,m,s);
    }
}
