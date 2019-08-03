package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 3) {
                        try {
                            if ("ж".equals(args[i + 1])) {
                                allPeople.add(Person.createFemale(args[i], dateFormat.parse(args[i + 2])));
                            }
                            if ("м".equals(args[i + 1])) {
                                allPeople.add(Person.createMale(args[i], dateFormat.parse(args[i + 2])));
                            }
                            System.out.println(allPeople.size() - 1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case "-u":
                for (int i = 1; i < args.length; i += 4) {
                    synchronized (allPeople) {
                        int id = Integer.valueOf(args[i]);
                        if (id < allPeople.size()) {
                            Person p = allPeople.get(id);
                            p.setName(args[i + 1]);

                            Sex sex = Sex.MALE;
                            if ("ж".equals(args[i + 2])) sex = Sex.FEMALE;
                            p.setSex(sex);
                            try {
                                p.setBirthDay(dateFormat.parse(args[i + 3]));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;
            case "-d":
                for (int i = 1; i < args.length; i++) {
                    synchronized (allPeople) {
                        int id = Integer.valueOf(args[i]);
                        if (id < allPeople.size()) {
                            Person p = allPeople.get(id);
                            p.setBirthDay(null);
                            p.setSex(null);
                            p.setName(null);
                        }
                    }
                }
                break;
            case "-i":
                for (int i = 1; i < args.length; i++) {
                    synchronized (allPeople) {
                        System.out.println(allPeople.get(Integer.valueOf(args[i])));
                    }
                }
                break;
        }
    }
}
