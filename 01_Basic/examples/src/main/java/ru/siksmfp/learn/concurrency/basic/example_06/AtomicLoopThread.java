package ru.siksmfp.learn.concurrency.basic.example_06;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicLoopThread implements Runnable {

    public AtomicBoolean isStopped = new AtomicBoolean(false);

    @Override
    public void run() {
        long count = 0;
        while (!isStopped.get()) {
            count++;
        }

        System.out.println("Atomic thread terminated " + count);
    }
}
