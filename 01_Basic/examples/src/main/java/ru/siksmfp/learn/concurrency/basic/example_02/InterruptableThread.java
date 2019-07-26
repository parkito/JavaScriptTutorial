package ru.siksmfp.learn.concurrency.basic.example_02;

public class InterruptableThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("I am running....");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Exiting");
                return;
            }
        }
    }
}
