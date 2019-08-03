package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH + "withdraw"
    );
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);

        while (true) {
            int amount = 0;
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                amount = Integer.parseInt(ConsoleHelper.readString());
                if (amount <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(amount)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }

            Map<Integer, Integer> withdrawAmount = null;
            try {
                withdrawAmount = manipulator.withdrawAmount(amount);
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            List<Integer> denominations = new LinkedList<>(withdrawAmount.keySet());
            Collections.sort(denominations, Collections.reverseOrder());
            for (int denomination : denominations) {
                ConsoleHelper.writeMessage(String.format("\t%d - %d", denomination, withdrawAmount.get(denomination)));
            }
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, manipulator.getCurrencyCode()));
            break;
        }
    }
}
