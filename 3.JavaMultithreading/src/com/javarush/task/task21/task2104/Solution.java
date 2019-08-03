package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Solution)) return false;
        Solution other = (Solution) obj;

        boolean firstEquals, lastEquals;
        if (first == null) {
            firstEquals = other.first == null;
        } else {
            firstEquals = first.equals(other.first);
        }

        if (last == null) {
            lastEquals = other.last == null;
        } else {
            lastEquals = last.equals(other.last);
        }

        return firstEquals && lastEquals;
    }

    @Override
    public int hashCode() {
        int firstHash = (first == null) ? 0 : first.hashCode();
        int lastHash = (last == null) ? 0 : last.hashCode();
        return 31 * firstHash + lastHash;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
