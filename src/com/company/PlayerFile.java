package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class PlayerFile {
    private List<User> userList = new ArrayList<>();
    private String filename = "users.json";

    private static PlayerFile playerFile = null;

    public static PlayerFile getInstance(){
        if (playerFile == null) {
            playerFile = new PlayerFile();
        }
        return playerFile;
    }

    public  String[][] getUserList(){
        String[][] data = new String[userList.size()][3];
        for (int i = 0; i < userList.size(); i++) {
                data[i][0] = String.valueOf(i+1);
                data[i][1] = userList.get(i).getName();
                data[i][2] = GameTimer.convertToTimeString(userList.get(i).getTime());
        }
        return data;
    }

    public void add(User user){
        userList.sort(Comparator.comparing(User::getTime));
        if (userList.size() < 10) {
            userList.add(user);
            write();
        } else {
            User minUser = userList.get(9);
            if (user.getTime() < minUser.getTime()){
                userList.remove(minUser);
                userList.add(user);
                write();
            }
        }
        userList.sort(Comparator.comparing(User::getTime));
    }

    public void write(){
        try {
            Writer writer = new FileWriter(filename);
            new Gson().toJson(userList,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        List<User> userArray = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            Type type = new TypeToken<List<User>>(){}.getType();
            userArray = new Gson().fromJson(br, type);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.userList = userArray;
    }
}
