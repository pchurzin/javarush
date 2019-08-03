package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        try {
            System.out.println(new Solution(4));
            Solution sol1 = new Solution(45);
            File file = new File("/home/cpv/work/javarush/2014.txt");

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

            out.writeObject(sol1);
            out.close();
            Solution sol2 = (Solution)in.readObject();

            System.out.println(sol1.string.equals(sol2.string));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
