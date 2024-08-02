package org.example;

import org.example.server.MessServer;

import java.io.IOException;

public class Main {
    private static final int DEFAULT_PORT = 1234;

    public static void main(String[] args) {
        int port = args.length == 0 || args[1] == null || args[1].isBlank() ? DEFAULT_PORT : Integer.parseInt(args[1]);
        try {
            Thread t = new MessServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}