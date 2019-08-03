package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return 31 * age + (name == null ? 0 : name.hashCode());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (!(obj instanceof User)) return false;
            User u = (User) obj;

            if (age != u.age) return false;
            if (name != null ? !name.equals(u.name) : u.name != null) return false;
            return true;
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            User n = (User) super.clone();
            n.name = name;
            n.age = age;
            return n;
        }
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        Solution n = (Solution) super.clone();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            n.users.put(entry.getKey(), entry.getValue().clone());
        }
        return n;
    }
}
