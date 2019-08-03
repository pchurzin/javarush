package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String grandFatherName = reader.readLine();
        Cat grandFather = new Cat(grandFatherName);

        String grandMotherName = reader.readLine();
        Cat grandMother = new Cat(grandMotherName);

        String fatherName = reader.readLine();
        Cat father = new Cat(fatherName, grandFather, null);

        String motherName = reader.readLine();
        Cat mother = new Cat(motherName, null, grandMother);

        String sonName = reader.readLine();
        Cat son = new Cat(sonName, father, mother);

        String daughterName = reader.readLine();
        Cat daughter = new Cat(daughterName, father, mother);


        System.out.println(grandFather);
        System.out.println(grandMother);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(son);
        System.out.println(daughter);

    }

    public static class Cat {
        private String name;
        private Cat father;
        private Cat mother;

        Cat(String name) {
            this.name = name;
        }

        Cat(String name, Cat father, Cat mother) {
            this.name = name;
            this.father = father;
            this.mother = mother;
        }

        @Override
        public String toString() {
            String s = "Cat name is " + name;

            if (mother == null)
                s += ", no mother ";
            else
                s += ", mother is " + mother.name;

            if (father == null)
                s += ", no father ";
            else
                s += ", father is " + father.name;

            return s;
        }
    }

}
