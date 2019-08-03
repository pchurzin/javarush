package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Human gf1 = new Human("gf1", true, 90);
        Human gf2 = new Human("gf2", true, 80);
        Human gm1 = new Human("gm1", false, 89);
        Human gm2 = new Human("gm2", false, 79);
        Human dad = new Human("dad", true, 30, gf1, gm1);
        Human mom = new Human("mom", false, 29, gf2, gm2);
        Human ch1 = new Human("ch1", true, 10, dad, mom);
        Human ch2 = new Human("ch2", false, 8, dad, mom);
        Human ch3 = new Human("ch3", true, 6, dad, mom);

        System.out.println(gf1);
        System.out.println(gf2);
        System.out.println(gm1);
        System.out.println(gm2);
        System.out.println(dad);
        System.out.println(mom);
        System.out.println(ch1);
        System.out.println(ch2);
        System.out.println(ch3);
    }

    public static class Human {
        //напишите тут ваш код
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }
}






















