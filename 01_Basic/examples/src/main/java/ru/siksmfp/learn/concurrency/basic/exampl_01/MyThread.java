package ru.siksmfp.learn.concurrency.basic.exampl_01;

public class MyThread extends Thread {
    private PingPong pingPong;

    public MyThread(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            pingPong.print();
        }
    }
}
