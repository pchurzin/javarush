package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH + "info"
    );
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulators.isEmpty()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
            return;
        }
        boolean noMoney = true;
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (!manipulator.hasMoney()) {
                continue;
            }
            ConsoleHelper.writeMessage(String.format("%s - %d", manipulator.getCurrencyCode(), manipulator.getTotalAmount()));
            noMoney = false;
        }
        if (noMoney) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
