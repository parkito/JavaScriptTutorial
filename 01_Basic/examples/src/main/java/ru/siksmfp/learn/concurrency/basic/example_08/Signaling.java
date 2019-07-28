package ru.siksmfp.learn.concurrency.basic.example_08;

public class Signaling {
    public static void main(String[] args) {
        ThreadExchangePoint exchangePoint = new ThreadExchangePoint();

        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> exchangePoint.send("From sender 1")
            ).start();

            new Thread(
                    () -> exchangePoint.send("From sender 2")
            ).start();

            new Thread(
                    () -> System.out.println("Reciever 1 recieved " + exchangePoint.receive())
            ).start();

            new Thread(
                    () -> System.out.println("Reciever 2 recieved " + exchangePoint.receive())
            ).start();
        }
    }
}
