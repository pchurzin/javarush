package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            while (in.ready()) {
                String[] parts = in.readLine().split("\\s");
                Date date = dateFormat.parse(parts[parts.length - 3] + "." + parts[parts.length - 2] + "." + parts[parts.length - 1]);
                StringBuilder name = new StringBuilder(parts[0]);
                for (int i = 1; i < parts.length - 3; i++) {
                    name.append(" ").append(parts[i]);
                }
                PEOPLE.add(new Person(name.toString(), date));
            }
            in.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        /*for (Person p : PEOPLE) {
            System.out.println(p.getName()  + " " + p.getBirthday());
        }*/
    }
}
