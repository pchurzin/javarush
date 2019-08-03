package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date d;
        try {
            d = dateFormat.parse("31.12.1986");
            System.out.println(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner scanner) {
            this.fileScanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            String[] parts  = fileScanner.nextLine().split(" ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date d;
            try {
                d = dateFormat.parse(parts[3] + "." + parts[4] + "." + parts[5]);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }

            return new Person(parts[1], parts[2], parts[0], d);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
