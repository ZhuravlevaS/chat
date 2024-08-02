package org.example.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.Message;

import java.net.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MessServer extends Thread {
    private ServerSocket serverSocket;

    public MessServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        System.out.println("Ожидание клиента на порт " +
                serverSocket.getLocalPort() + "...");
        Socket server = null;
        try {
            server = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            while (true) {

                DataInputStream in = new DataInputStream(server.getInputStream());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                Message message = new Message(LocalDate.now().toString(), "Sender", in.readUTF());

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(message);
                System.out.println(in.readUTF());
                System.out.println(json);

                out.writeUTF(json);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}