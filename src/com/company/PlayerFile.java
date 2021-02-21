package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class PlayerFile {
    private List<User> userList1 = new ArrayList<>();
    private List<User> userList2 = new ArrayList<>();
    private List<User> userList3 = new ArrayList<>();
    private String filename = "";

    private static PlayerFile playerFile = null;

    public static PlayerFile getInstance(){
        if (playerFile == null) {
            playerFile = new PlayerFile();
        }
        return playerFile;
    }


    public  String[][] getUserList(){
        List<User> currentList = getCurrentUserList();
        String[][] data = new String[currentList.size()][3];
        for (int i = 0; i < currentList.size(); i++) {
                data[i][0] = String.valueOf(i+1);
                data[i][1] = currentList.get(i).getName();
                data[i][2] = GameTimer.convertToTimeString(currentList.get(i).getTime());
        }
        return data;
    }

    public void add(User user){
        List<User> currentList = getCurrentUserList();
        currentList.sort(Comparator.comparing(User::getTime));
        if (currentList.size() < 10) {
            currentList.add(user);
            write();
        } else {
            User minUser = currentList.get(9);
            if (user.getTime() < minUser.getTime()){
                currentList.remove(minUser);
                currentList.add(user);
                write();
            }
        }
        currentList.sort(Comparator.comparing(User::getTime));
    }

    public void write(){
        try {
            Writer writer = new FileWriter(filename);
            new Gson().toJson(getCurrentUserList(),writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        setFilename();
        List<User> userArray = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Type type = new TypeToken<List<User>>(){}.getType();
            userArray = new Gson().fromJson(br, type);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCurrentUserList(userArray);
        System.out.println("ddd");
    }


    private void setCurrentUserList(List<User> list){
        switch (GamePanel.level){
            case Easy:
                userList1 = list;
                break;
            case Medium:
                userList2 = list;
                break;
            case Hard:
                userList3 = list;
                break;
        }
    }


    private List<User> getCurrentUserList(){
        List<User> list = new ArrayList<>();
        switch (GamePanel.level){
            case Easy:
                list = userList1;
                break;
            case Medium:
                list = userList2;
                break;
            case Hard:
                list = userList3;
                break;
        }
        return list;
    }

    private void setFilename(){
        switch (GamePanel.level){
            case Easy:
                filename = "users1.json";
                break;
            case Medium:
                filename = "users2.json";
                break;
            case Hard:
                filename = "users3.json";
                break;
        }
    }
}
