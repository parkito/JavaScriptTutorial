package ru.siksmfp.learn.concurrency.basic.exampl_01;

public class Creation {

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();

        MyThread thread1 = new MyThread(pingPong);
        MyThread thread2 = new MyThread(pingPong);

        thread1.start();
        thread2.start();

        MyRunnable runnable1 = new MyRunnable(pingPong);
        MyRunnable runnable2 = new MyRunnable(pingPong);

        Thread runThread1 = new Thread(runnable1);
        Thread runThread2 = new Thread(runnable2);

        runThread1.start();
        runThread2.start();
    }
}
