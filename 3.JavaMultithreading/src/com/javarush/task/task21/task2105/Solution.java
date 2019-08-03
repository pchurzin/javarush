package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;
        boolean firstEquals, lastEquals;
        if (first == null) {
            firstEquals = n.first == null;
        } else {
            firstEquals = first.equals(n.first);
        }
        if (last == null) {
            lastEquals = n.last == null;
        } else {
            lastEquals = last.equals(n.last);
        }

        return firstEquals && lastEquals;
    }

    @Override
    public int hashCode() {
        int hash1 = first == null ? 0 : first.hashCode();
        int hash2 = last == null ? 0 : last.hashCode();
        return 31 * hash1 + hash2;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
