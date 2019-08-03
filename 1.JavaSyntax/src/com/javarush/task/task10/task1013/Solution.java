package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        // напишите тут ваши переменные и конструкторы
        private String name;
        private boolean sex;
        private int age;
        private String address;
        private int weight;
        private int height;

        public Human(String name) {
            this.name = name;
        }

        public Human(String name, boolean sex) {
            this.name = name;
            this.sex = sex;
        }

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, int height, int weight) {
            this.name = name;
            this.height = height;
            this.weight = weight;
        }

        public Human(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public Human(String name, int age, int height, int weight) {
            this.name = name;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }

        public Human(String name, boolean sex, int age, int height, int weight, String address) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.address = address;
        }

        public Human(String name, boolean sex, int height, int weight) {
            this.name = name;
            this.sex = sex;
            this.weight = weight;
            this.height = height;
        }

        public Human(String name, String address, int age) {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public Human(String name, String address, boolean sex) {
            this.name = name;
            this.sex = sex;
            this.address = address;
        }
    }
}
