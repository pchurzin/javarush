package com.javarush.task.task27.task2710;

public class MailServer implements Runnable {
    private Mail mail;

    public MailServer(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        try {
            synchronized (mail) {
                while (mail.getText() == null || mail.getText().isEmpty())
                    mail.wait();
            }
        } catch (InterruptedException ignored) {

        }
        //сделайте что-то тут - do something here
        String name = Thread.currentThread().getName();
        long afterTime = System.currentTimeMillis();
        System.out.format("%s MailServer has got: [%s] in %d ms after start", name, mail.getText(), (afterTime - beforeTime));
    }
}
