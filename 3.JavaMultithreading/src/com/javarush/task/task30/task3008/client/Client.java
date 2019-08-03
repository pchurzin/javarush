package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread {
        @Override
        public void run() {
                String serverAddress = getServerAddress();
                int port = getServerPort();
                try (Socket socket = new Socket(serverAddress, port);
                     Connection connection = new Connection(socket)) {
                    Client.this.connection = connection;
                    clientHandshake();
                    clientMainLoop();
                    Client.this.notify();
                } catch (IOException | ClassNotFoundException e) {
                    notifyConnectionStatusChanged(false);
                }
        }

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " joined chat");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " leaved chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String name = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, name));

                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());

                } else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());

                } else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());

                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Client().run();
    }

    protected String getServerAddress() {
        String ip = ConsoleHelper.readString();
        return ip;
    }

    protected int getServerPort() {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread thread = getSocketThread();
        thread.setDaemon(true);
        thread.start();
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage(e.getMessage());
                return;
            }
            if (!clientConnected) {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
                return;
            }

            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
            while (clientConnected) {
                String text = ConsoleHelper.readString();
                if ("exit".equals(text)) break;
                if (shouldSendTextFromConsole()) {
                    sendTextMessage(text);
                }
            }
        }
    }
}
