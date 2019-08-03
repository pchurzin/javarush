package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        //напишите тут ваш код
        HashMap<String, Cat> map = new HashMap<>();
        map.put("Cat1", new Cat("Cat1"));
        map.put("Cat2", new Cat("Cat2"));
        map.put("Cat3", new Cat("Cat3"));
        map.put("Cat4", new Cat("Cat4"));
        map.put("Cat5", new Cat("Cat5"));
        map.put("Cat6", new Cat("Cat6"));
        map.put("Cat7", new Cat("Cat7"));
        map.put("Cat8", new Cat("Cat8"));
        map.put("Cat9", new Cat("Cat9"));
        map.put("Cat10", new Cat("Cat10"));
        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        //напишите тут ваш ко
        return new HashSet<Cat>(map.values());
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
