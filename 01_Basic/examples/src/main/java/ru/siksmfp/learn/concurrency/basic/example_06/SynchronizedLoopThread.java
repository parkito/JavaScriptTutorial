package ru.siksmfp.learn.concurrency.basic.example_06;

public class SynchronizedLoopThread implements Runnable {

    public boolean isStopped = false;

    @Override
    public void run() {
        long count = 0;
        while (!isStopped) {
            synchronized (this) {
                count++;
            }
        }

        System.out.println("Synchronized thread terminated " + count);
    }
}
