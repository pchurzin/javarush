package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
//            File your_file_name = File.createTempFile("your_file_name", null);
            File your_file_name = new File("/home/cpv/work/javarush/2002.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            User user = new User();
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            user.setBirthDate(new Date());
            user.setLastName("Ivanov");
            user.setFirstName("Ivan");
            javaRush.users.add(user);
            user = new User();
            user.setMale(false);
            user.setCountry(User.Country.UKRAINE);
            user.setBirthDate(new Date());
            user.setLastName("Petrova");
            user.setFirstName("Olga");
            javaRush.users.add(user);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();
        private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintStream out = new PrintStream(outputStream);
            for (User user : users) {
//                outputStream.write((user.getFirstName() + "\n");
                out.println(user.getFirstName());
                out.println(user.getLastName());
//                out.println(dateFormat.format(user.getBirthDate()));
                out.println(user.getBirthDate().getTime());
                out.println(user.isMale());
                out.println(user.getCountry());
            }
            out.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            Scanner in = new Scanner(inputStream);
            in.useDelimiter("\n");
            while (in.hasNext()) {
                User user = new User();
                user.setFirstName(in.next());
                user.setLastName(in.next());
                user.setBirthDate(new Date(in.nextLong()));
                user.setMale(in.nextBoolean());
                String country = in.next();
                for (User.Country c : User.Country.values()) {
                    if (country.equals(c.toString())) {
                        user.setCountry(c);
                        break;
                    }
                }
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
