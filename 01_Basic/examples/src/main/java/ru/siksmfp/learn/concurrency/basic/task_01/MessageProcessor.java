package ru.siksmfp.learn.concurrency.basic.task_01;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageProcessor {

    private static final String SUCCESS_RESPONSE = "Data received";

    private Socket socket;

    public MessageProcessor(Socket socket) {
        this.socket = socket;
    }

    public void processMessage() {
        try (BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
            String inputData = new String(bis.readAllBytes());
            System.out.println(inputData);
            pw.println(SUCCESS_RESPONSE);
        } catch (IOException ex) {
            throw new IllegalStateException("Error occurred during message processing", ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Can't close socket correctly");
                ex.printStackTrace();
            }
        }
    }
}
