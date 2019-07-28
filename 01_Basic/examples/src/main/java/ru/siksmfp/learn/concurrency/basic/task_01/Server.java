package ru.siksmfp.learn.concurrency.basic.task_01;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(59898)) {
            System.out.println("Server is running");
            //TODO Allocate the thread for each connection
            while (true) {
                Socket connection = listener.accept();
                new MessageProcessor(connection).processMessage();
            }
        }
    }
}

// echo "Some data to send" | nc localhost 59898