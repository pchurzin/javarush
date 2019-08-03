package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket socket = new Socket(url.getHost(), 80);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(baos);
//            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.write("GET " + url.getFile() + " HTTP/1.0\n");
            out.write("Host: " + url.getHost() + "\n\n");
            out.flush();
            socket.getOutputStream().write(baos.toByteArray());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}