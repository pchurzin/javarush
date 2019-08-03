package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            amount += entry.getKey() * entry.getValue();
        }
        return amount;
    }

    public boolean hasMoney() {
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();
        if (expectedAmount <= 0) {
            return result;
        }
        Map<Integer, Integer> denominationsCopy = new HashMap<>(denominations);
        List<Integer> denoms = new LinkedList<>(denominationsCopy.keySet());
        Collections.sort(denoms, Collections.reverseOrder());
        int amountLeft = expectedAmount;
        for (int d : denoms) {
            int banknoteMaxCount = amountLeft / d;
            int banknoteCount = Math.min(banknoteMaxCount, denominationsCopy.get(d));
            if (banknoteCount == 0) {
                continue;
            }
            result.put(d, banknoteCount);
            denominationsCopy.put(d, denominationsCopy.get(d) - banknoteCount);
            amountLeft -= d * banknoteCount;
            if (amountLeft <= 0) {
                break;
            }
        }
        if (amountLeft != 0) {
            throw new NotEnoughMoneyException();
        }
        denominations = new HashMap<>(denominationsCopy);
        return result;
    }
}
