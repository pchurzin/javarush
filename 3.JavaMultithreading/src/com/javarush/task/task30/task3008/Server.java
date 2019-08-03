package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)){
            ConsoleHelper.writeMessage("Server started");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Handler(clientSocket).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message nameMessage = connection.receive();
                if (nameMessage.getType() != MessageType.USER_NAME) continue;
                String name = nameMessage.getData();
                if (name.isEmpty()) continue;
                if (connectionMap.containsKey(name)) continue;
                connectionMap.put(name, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return name;
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (name.equals(userName)) continue;
                Message message = new Message(MessageType.USER_ADDED, name);
                connection.send(message);
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    Message chatMessage = new Message(MessageType.TEXT, userName + ": " + message.getData());
                    sendBroadcastMessage(chatMessage);
                } else {
                    ConsoleHelper.writeMessage("Not a text message");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Connection established with " + socket.getRemoteSocketAddress());
            String name = null;
            try (Connection connection = new Connection(socket)) {
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                sendListOfUsers(connection, name);
                serverMainLoop(connection, name);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Exception thrown while exchanging data with " +
                                           socket.getRemoteSocketAddress() + ": " +
                                           e.getMessage());
            }
            if (name != null) {
                connectionMap.remove(name);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
            }

            ConsoleHelper.writeMessage("Connection with " + socket.getRemoteSocketAddress() + " was closed");
        }
    }
}
