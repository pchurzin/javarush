package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH + "common"
    );


    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String s = bis.readLine();
            if ("EXIT".equalsIgnoreCase(s)) {
                throw new InterruptOperationException();
            }
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String currencyCode = readString();
            if (currencyCode.length() != 3) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            return currencyCode.toUpperCase();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String[] input = readString().split(" ");
            if (input.length != 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            try {
                int denomination = Integer.parseInt(input[0]);
                int count = Integer.parseInt(input[1]);
                if (count <= 0 || denomination <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            return input;
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            printOperationsMenu();
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

    private static void printOperationsMenu() {
        writeMessage("1 - " + res.getString("operation.INFO"));
        writeMessage("2 - " + res.getString("operation.DEPOSIT"));
        writeMessage("3 - " + res.getString("operation.WITHDRAW"));
        writeMessage("4 - " + res.getString("operation.EXIT"));
    }
}
