package com.javarush.task.task12.task1226;

/* 
Лазать, летать и бегать
*/

public class Solution {

    public interface Run {
        void run();
    }

    public interface Climb {
        void climb();
    }

    public interface Fly {
        void fly();
    }

    public class Cat implements Run, Climb {
        @Override
        public void climb() {

        }

        @Override
        public void run() {

        }
    }

    public class Dog implements Run {
        @Override
        public void run() {

        }
    }

    public class Tiger extends Cat {
    }

    public class Duck implements Fly, Run {
        @Override
        public void run() {

        }

        @Override
        public void fly() {

        }
    }
}
