package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = PropertyResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.verifiedCards"
    );
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH + "login"
    );

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String card = null;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            card = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (card.length() != 12 || pin.length() != 4) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            if (!validCreditCards.containsKey(card) || !validCreditCards.getString(card).equals(pin)) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
            break;
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
    }
}
