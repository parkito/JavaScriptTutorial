package ru.siksmfp.learn.concurrency.basic.example_01;

public class MyRunnable implements Runnable {
    private UnsafePingPong unsafePingPong;

    public MyRunnable(UnsafePingPong unsafePingPong) {
        this.unsafePingPong = unsafePingPong;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            unsafePingPong.print();
        }
    }
}