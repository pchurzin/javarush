package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {

    }

    public static class ExceptionFactory {
        public static Throwable getException(Enum e) {
            if (e == null) {
                return new IllegalArgumentException();
            }

            String message = e.toString().replace('_', ' ').toLowerCase();
            message = message.substring(0,1).toUpperCase() + message.substring(1);
            if (e.getClass() == ExceptionApplicationMessage.class) {
                return new Exception(message);
            }
            if (e.getClass() == ExceptionDBMessage.class) {
                return new RuntimeException(message);
            }
            if (e.getClass() == ExceptionUserMessage.class) {
                return new Error(message);
            }

            return new IllegalArgumentException();
        }
    }
}