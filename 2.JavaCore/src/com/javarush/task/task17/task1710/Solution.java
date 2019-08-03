package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        if ("-c".equals(args[0]) && args.length == 4) {
            try {
                if ("ж".equals(args[2])) {
                    allPeople.add(Person.createFemale(args[1], dateFormat.parse(args[3])));
                }
                if ("м".equals(args[2])) {
                    allPeople.add(Person.createMale(args[1], dateFormat.parse(args[3])));
                }
                System.out.println(allPeople.size() - 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if ("-u".equals(args[0]) && args.length == 5) {
            int id = Integer.valueOf(args[1]);
            if (id < allPeople.size()) {
                Person p = allPeople.get(id);
                p.setName(args[2]);

                Sex sex = Sex.MALE;
                if ("ж".equals(args[3])) sex = Sex.FEMALE;
                p.setSex(sex);
                try {
                    p.setBirthDay(dateFormat.parse(args[4]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if ("-d".equals(args[0]) && args.length == 2) {
            int id = Integer.valueOf(args[1]);
            if (id < allPeople.size()) {
                Person p = allPeople.get(id);
                p.setBirthDay(null);
                p.setSex(null);
                p.setName(null);
            }
        }
        if ("-i".equals(args[0]) && args.length == 2) {
            System.out.println(allPeople.get(Integer.valueOf(args[1])));
        }
    }
}
