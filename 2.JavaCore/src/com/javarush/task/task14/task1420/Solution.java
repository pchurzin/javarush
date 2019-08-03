package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int i1 = Integer.parseInt(r.readLine());
        if (i1 <= 0) throw new Exception();
        int i2 = Integer.parseInt(r.readLine());
        if (i2 <= 0) throw new Exception();

        System.out.println(nod(i1, i2));
    }

    public static int nod(int m, int n) {
        if (m == 0) return n;
        if (n == 0) return m;
        if (m == n) return m;
        if (m == 1) return 1;
        if (n == 1) return 1;
        if (m % 2 == 0 && n % 2 == 0) return 2 * nod(m/2, n/2);
        if (m % 2 == 0 && n % 2 != 0) return nod(m/2, n);
        if (m % 2 != 0 && n % 2 == 0) return nod(m, n/2);
        if (m > n) return nod((m - n)/2, n);
        return nod(m, (n - m)/2); // m < n
    }
}
