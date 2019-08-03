package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH + "deposit"
    );
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        try {
            String[] addValues = ConsoleHelper.getValidTwoDigits(currencyCode);
            int i = Integer.parseInt(addValues[0]);
            int j = Integer.parseInt(addValues[1]);
            manipulator.addAmount(i, j);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), i * j, manipulator.getCurrencyCode()));
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
