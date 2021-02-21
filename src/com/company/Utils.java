package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Utils {

    private List<User> userList = new ArrayList<>();

    private String filename = "users.json";


    private static Utils utils = null;

    public static Utils getInstance(){
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public List<User> getList(){

        return userList;
    }

    public  String[][] getUserList(){
        String[][] data1 = new String[userList.size()][3];
        for (int i = 0; i < userList.size(); i++) {
                data1[i][0] = String.valueOf(i+1);
                data1[i][1] = userList.get(i).getName();
                data1[i][2] = GameTimer.convertToTimeString(userList.get(i).getTime());
        }

        return data1;
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
        userList.forEach(s->System.out.print(s.getTime() + " "));
    }

    public void write(){
        Writer writer = null;
        try {
            writer = new FileWriter(filename);
            new Gson().toJson(userList,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        List<User> userArray = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filename));

            Type type = new TypeToken<List<User>>(){}.getType();
            userArray = new Gson().fromJson(br, type);



            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.userList = userArray;
    }
}
