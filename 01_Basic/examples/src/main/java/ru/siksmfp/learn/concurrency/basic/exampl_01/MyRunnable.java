package ru.siksmfp.learn.concurrency.basic.exampl_01;

public class MyRunnable implements Runnable {
    private PingPong pingPong;

    public MyRunnable(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            pingPong.print();
        }
    }
}