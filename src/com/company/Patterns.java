package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Pattern {Disney, Animals, Fruit, Pokemon};

public class Patterns {
    private static Patterns patterns = null;

    public static Patterns getInstance(){
        if (patterns == null){
            patterns = new Patterns();
        }
        return patterns;
    }

    private String[] pokeList = {
            "src/images/pokemon/p1.png",
            "src/images/pokemon/p2.png",
            "src/images/pokemon/p3.png",
            "src/images/pokemon/p4.png",
            "src/images/pokemon/p5.jpeg",
            "src/images/pokemon/p6.png",
            "src/images/pokemon/p7.png",
            "src/images/pokemon/p8.png",
            "src/images/pokemon/p9.png"
    };

    private String[] animalList = {
            "src/images/animals/a1.jpg",
            "src/images/animals/a2.jpg",
            "src/images/animals/a3.png",
            "src/images/animals/a4.png",
            "src/images/animals/a5.jpg",
            "src/images/animals/a6.jpg",
            "src/images/animals/a7.jpg",
            "src/images/animals/a8.jpg",
            "src/images/animals/a9.jpg"
    };

    private String[] fruitList = {
            "src/images/fruit/f1.jpg",
            "src/images/fruit/f2.png",
            "src/images/fruit/f3.jpg",
            "src/images/fruit/f4.jpg",
            "src/images/fruit/f5.jpg",
            "src/images/fruit/f6.jpg",
            "src/images/fruit/f7.png",
            "src/images/fruit/f8.jpg",
            "src/images/fruit/f9.jpg"
    };

    private String[] disneyList = {
            "src/images/disney/c1.png",
            "src/images/disney/c2.jpg",
            "src/images/disney/c3.png",
            "src/images/disney/c4.png",
            "src/images/disney/c5.png",
            "src/images/disney/c6.png",
            "src/images/disney/c7.png",
            "src/images/disney/c8.png",
            "src/images/disney/c9.jpg"
    };

    public List<String> getPatterns(Pattern pattern) {
        List<String> list = new ArrayList<>();
        switch (pattern){
            case Pokemon:
                list = Arrays.asList(pokeList);
                break;
            case Fruit:
                list = Arrays.asList(fruitList);
                break;
            case Animals:
                list = Arrays.asList(animalList);
                break;
            case Disney:
                list = Arrays.asList(disneyList);
                break;
        }
        return list;
    }

}