package org.example.client.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String DEFAULT_NAME = "localhost";
    private static final int DEFAULT_PORT = 1234;
    private static Reader reader;
    private static Writer writer;

    public static void main(String [] args) {
        String serverName = args.length == 0 || args[0] == null || args[0].isBlank() ? DEFAULT_NAME : args[0];
        int port = args.length == 0 || args[1] == null || args[1].isBlank() ? DEFAULT_PORT : Integer.parseInt(args[1]);


        try {
            System.out.println("Подключение к " + serverName + " на порт " + port);
            Socket client = new Socket(serverName, port);

            InputStream inFromServer = client.getInputStream();
            OutputStream outToServer = client.getOutputStream();

            reader = new Reader(inFromServer);
            writer = new Writer(outToServer);

            Thread writerThread = new Thread(writer);
            writerThread.start();

            Thread readerThread = new Thread(reader);
            readerThread.start();


            if (!writer.isConnected()) client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

