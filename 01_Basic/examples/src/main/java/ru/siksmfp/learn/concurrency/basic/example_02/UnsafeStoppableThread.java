package ru.siksmfp.learn.concurrency.basic.example_02;

public class UnsafeStoppableThread implements Runnable {

    private boolean isStopped = false;

    public void doStop() {
        isStopped = true;
    }

    @Override
    public void run() {
        while (!isStopped) {
            System.out.println("Running ...");
        }
    }
}
