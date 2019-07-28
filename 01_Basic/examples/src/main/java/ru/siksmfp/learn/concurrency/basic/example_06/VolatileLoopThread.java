package ru.siksmfp.learn.concurrency.basic.example_06;

public class VolatileLoopThread implements Runnable {

    public volatile boolean isStopped = false;

    @Override
    public void run() {
        long count = 0;
        while (!isStopped) {
            count++;
        }

        System.out.println("Volatile thread terminated " + count);
    }
}
