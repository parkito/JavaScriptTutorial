package ru.siksmfp.learn.concurrency.basic.example_08;

public class ThreadExchangePoint {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean isSending = true;

    public synchronized void send(String packet) {
        while (!isSending) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted during send waiting");
            }
        }

        isSending = false;

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while (isSending) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted during recieve waiting");
            }
        }

        isSending = true;

        notifyAll();
        return packet;
    }
}
