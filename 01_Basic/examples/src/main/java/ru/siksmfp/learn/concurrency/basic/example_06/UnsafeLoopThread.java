package ru.siksmfp.learn.concurrency.basic.example_06;

public class UnsafeLoopThread implements Runnable {

    public boolean isStopped = false;

    @Override
    public void run() {
        long count = 0;
        while (!isStopped) {
            count++;
        }

        System.out.println("Unsafe thread terminated " + count);
    }
}
