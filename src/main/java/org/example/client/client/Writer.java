package org.example.client.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Writer implements Runnable {

    private DataOutputStream output;
    private boolean isConnected;

    public Writer(OutputStream output) {
        this.output = new DataOutputStream(output);
        this.isConnected = true;
    }

    @Override
    public void run() {
        while (isConnected) {
            System.out.println("Введите сообщение");
            Scanner scr = new Scanner(System.in);
            String text = scr.nextLine();
            if (text.equalsIgnoreCase("Leave chat")) isConnected = false;
            try {
                output.writeUTF(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
