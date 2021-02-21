package com.company;

import com.google.gson.Gson;
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

    public void add(User user){
        if (userList.size() < 10) {
            userList.add(user);
            userList.sort(Comparator.comparing(User::getTime));
            write();
        } else {
            User minUser = userList.get(9);
            if (user.getTime() < minUser.getTime()){
                userList.remove(minUser);
                userList.add(user);
                userList.sort(Comparator.comparing(User::getTime));
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
            userArray = new Gson().fromJson(br, (Type) User[].class);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.userList = userArray;
    }
}
