package com.company;

public class User {
    private String name;
    private int time;

    public User(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}
