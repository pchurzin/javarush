package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fn;
        try {
            fn = r.readLine();
            FileInputStream inputStream = new FileInputStream(fn);
            load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties p = new Properties();
        for (Map.Entry<String, String> prop : properties.entrySet()) {
            p.setProperty(prop.getKey(), prop.getValue());
        }
        p.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties p = new Properties();
        p.load(inputStream);
        for (Map.Entry<Object, Object> prop: p.entrySet()) {
            properties.put((String)prop.getKey(), (String)prop.getValue());
        }
    }

    public static void main(String[] args) {

    }
}
