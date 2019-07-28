package ru.siksmfp.learn.concurrency.basic.example_09;

import java.util.Date;

public class Deadlock {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    private static class Thread1 implements Runnable {

        @Override
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1..." + new Date().getTime());

                System.out.println("Thread 1: Waiting for lock 2..." + new Date().getTime());

                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2..." + new Date().getTime());
                }
            }
        }
    }

    private static class Thread2 implements Runnable {

        @Override
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding lock 2..." + new Date().getTime());

                System.out.println("Thread 2: Waiting for lock 1..." + new Date().getTime());

                synchronized (Lock1) {
                    System.out.println("Thread 2: Holding lock 1 & 2..." + new Date().getTime());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();

        System.out.println("Main thread is finished");
    }
}