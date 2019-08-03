package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.text.SimpleDateFormat;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        new SimpleDateFormat().parse("non parse");
    }

    public static void main(String[] args) {

    }
}
