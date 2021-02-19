package com.company;

import java.util.Arrays;

public enum Level {
    Hard,
    Medium,
    Easy;

    public static String[] getLevelList(){
        return Arrays.stream(values()).map(e -> e.toString()).toArray(String[]::new);
    }

    public static Level getLevel(int index){
        return values()[index];
    }
}
