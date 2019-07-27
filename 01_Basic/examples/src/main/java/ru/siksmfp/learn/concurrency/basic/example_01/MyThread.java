package ru.siksmfp.learn.concurrency.basic.example_01;

public class MyThread extends Thread {
    private UnsafePingPong unsafePingPong;

    public MyThread(UnsafePingPong unsafePingPong) {
        this.unsafePingPong = unsafePingPong;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            unsafePingPong.print();
        }
    }
}
