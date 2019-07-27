package ru.siksmfp.learn.concurrency.basic.example_04;

public class SynchronizedPingPong {
    private int count;

    public synchronized void fullSyncPrint() {
        if (count % 2 == 0) {
            System.out.print("ping ");
            System.out.print(count);
        } else {
            System.out.print("pong ");
            System.out.print(count);
        }
        System.out.print(" ");
        System.out.println(Thread.currentThread().getName());
        count++;
    }

    public void syncPrint() {
        synchronized (this) {
            if (count % 2 == 0) {
                System.out.print("ping ");
                System.out.print(count);
            } else {
                System.out.print("pong ");
                System.out.print(count);
            }
            System.out.print(" ");
            System.out.println(Thread.currentThread().getName());
            count++;
        }
    }

    public void classSyncPrint() {
        synchronized (SynchronizedPingPong.class) {
            if (count % 2 == 0) {
                System.out.print("ping ");
                System.out.print(count);
            } else {
                System.out.print("pong ");
                System.out.print(count);
            }
            System.out.print(" ");
            System.out.println(Thread.currentThread().getName());
            count++;
        }
    }
}