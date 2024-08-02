package org.example.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.common.Message;

import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;

public class Reader implements Runnable {
    private final DataInputStream input;

    public Reader(InputStream input) {
        this.input = new DataInputStream(input);

    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                System.out.println("Mess: " + input.readUTF());
                Message message = objectMapper.readValue(input.readUTF(), Message.class);

                String text = message.time() + " " + message.sender() + " " + message.text();
                FileWriter writer = new FileWriter("massages.txt");

                writer.write(text);

            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
