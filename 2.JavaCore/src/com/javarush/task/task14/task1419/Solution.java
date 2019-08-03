package com.javarush.task.task14.task1419;



import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Object o = new String();
            ((Double)o).floatValue();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            String s = null;
            s.isEmpty();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            int[] ints = {1, 2, 3};
            int i = ints[3];
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            ArrayList<Object> o = new ArrayList<>();
            o.get(2);
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            FileInputStream fis = new FileInputStream("\\nofile");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Class.forName("noclass");
        } catch (Exception e) {
            exceptions.add(e);
        }

        exceptions.add(new IllegalArgumentException());
        exceptions.add(new IllegalStateException());
        exceptions.add(new IllegalThreadStateException());

        //напишите тут ваш код

    }
}
