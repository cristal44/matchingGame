package com.company;

import java.util.Arrays;

public enum Pattern {
    Animals,
    Flowers,
    Sports,
    Shapes;

    public static String[] getPatternList(){
        return Arrays.stream(values()).map(e -> e.toString()).toArray(String[]::new);
    }

    public static Pattern getPattern(int index){
        return values()[index];
    }
}
