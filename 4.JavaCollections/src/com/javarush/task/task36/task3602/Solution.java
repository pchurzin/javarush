package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for (Class clazz : Collections.class.getDeclaredClasses()) {
            try {
                for (Constructor constructor : clazz.getDeclaredConstructors()) {
                    if (constructor.getParameterCount() > 0) {
                        continue;
                    }
                    constructor.setAccessible(true);
                    ((List) constructor.newInstance()).get(0);
                }
            } catch (IndexOutOfBoundsException ignore) {
                return clazz;
            } catch (Exception ignore) {

            }
        }
        return null;
    }
}
