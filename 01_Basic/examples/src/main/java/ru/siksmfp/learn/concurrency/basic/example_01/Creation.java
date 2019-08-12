package ru.siksmfp.learn.concurrency.basic.example_01;

public class Creation {

    public static void main(String[] args) {
        UnsafePingPong unsafePingPong = new UnsafePingPong();

        MyThread thread1 = new MyThread(unsafePingPong);
        MyThread thread2 = new MyThread(unsafePingPong);

        thread1.start();
        thread2.start();

        MyRunnable runnable1 = new MyRunnable(unsafePingPong);
        MyRunnable runnable2 = new MyRunnable(unsafePingPong);

        Thread runThread1 = new Thread(runnable1);
        Thread runThread2 = new Thread(runnable2);

        runThread1.start();
        runThread2.start();

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        new Thread(runnable).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName()));
    }
}
