package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Human gf1 = new Human();
        gf1.name = "gf1";
        gf1.age = 90;
        gf1.sex = true;

        Human gf2 = new Human();
        gf2.name = "gf2";
        gf2.age = 90;
        gf2.sex = true;

        Human gm1 = new Human();
        gm1.name = "gm1";
        gm1.age = 89;
        gm1.sex = false;

        Human gm2 = new Human();
        gm2.name = "gm2";
        gm2.age = 89;
        gm2.sex = false;

        Human f = new Human();
        f.name = "f";
        f.age = 30;
        f.sex = true;

        Human m = new Human();
        m.name = "m";
        m.sex = false;
        m.age = 29;

        Human ch1 = new Human();
        ch1.name = "ch1";
        ch1.age = 5;
        ch1.sex = false;

        Human ch2 = new Human();
        ch2.name = "ch2";
        ch2.age = 5;
        ch2.sex = true;

        Human ch3 = new Human();
        ch3.name = "ch3";
        ch3.age = 10;
        ch3.sex = false;

        f.children.add(ch1);
        f.children.add(ch2);
        f.children.add(ch3);

        m.children.add(ch1);
        m.children.add(ch2);
        m.children.add(ch3);

        gf1.children.add(f);
        gm1.children.add(f);

        gf2.children.add(m);
        gm2.children.add(m);

        System.out.println(gf1);
        System.out.println(gm1);
        System.out.println(gf2);
        System.out.println(gm2);
        System.out.println(f);
        System.out.println(m);
        System.out.println(ch1);
        System.out.println(ch2);
        System.out.println(ch3);

    }

    public static class Human {
        //напишите тут ваш код
        public String name;
        public boolean sex;
        public int age;
        public ArrayList<Human> children = new ArrayList<>();

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
