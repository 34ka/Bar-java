package ru.stqa.pft.sandboxx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
//        String[] langs = new String[4];
//        langs[0] = "Java";
//        langs[1] = "C#";
//        langs[2] = "Python";
//        langs[3] = "PHP";

// тоже самое только одной строчкой
        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

        //размер списка можно изменить, просто добавлять "Язык программирования" и всё. А размер массива выше изменить нельзя т.к. он уже ограничен кол-вом, которое в него обозначено (4).

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
