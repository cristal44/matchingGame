package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer{
    private static Timer timer;
    private static int hour;
    private static int minute;
    private static int second;
    private static int elapsedTime;

    private static final int MILLISECONDS_HOUR = 3600000;
    private static final int MILLISECONDS_MINUTE= 60000;
    private static final int MILLISECONDS_SECOND = 1000;
    private static final int SECOND = 60;
    private static final int RESET_TIMER = 0;

    private static JLabel label;

    public static Timer getTimerInstance(){
        return timer;
    }

    public static void init (JLabel label){
        new GameTimer(label);
    }

    private GameTimer(JLabel label) {
        this.label = label;
        timer = new Timer(MILLISECONDS_SECOND, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime = elapsedTime + MILLISECONDS_SECOND;
                setTime(elapsedTime);

                label.setText(String.format("%02d:%02d:%02d",hour,minute,second));
            }
        });
    }

    public static void reset(){
        hour = RESET_TIMER;
        minute = RESET_TIMER;
        second = RESET_TIMER;
        elapsedTime = RESET_TIMER;

        label.setText(String.format("%02d:%02d:%02d",hour,minute,second));
    }

    public static int getElapsedTime() {
        return elapsedTime;
    }

    public static String convertToTimeString(int elapsedTime){
        setTime(elapsedTime);
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }

    public static void setTime(int elapsedTime){
        hour = (elapsedTime/MILLISECONDS_HOUR);
        minute = (elapsedTime/MILLISECONDS_MINUTE) % SECOND;
        second = (elapsedTime/MILLISECONDS_SECOND) % SECOND;
    }
}
