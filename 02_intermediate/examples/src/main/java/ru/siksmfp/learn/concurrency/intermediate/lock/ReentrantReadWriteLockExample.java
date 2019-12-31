package ru.siksmfp.learn.concurrency.intermediate.lock;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//The read lock may be held simultaneously by multiple reader threads, so long as there are no writers.
// The write lock is exclusive.
public class ReentrantReadWriteLockExample {

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                lock.readLock().lock();
                try {
                    System.out.println(System.currentTimeMillis() + " Read lock is acquired " + Thread.currentThread().getName());
                    Utils.sleepSeconds(10);
                } finally {
                    lock.readLock().unlock();
                    System.out.println(System.currentTimeMillis() + " Read lock is released " + Thread.currentThread().getName());
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                lock.writeLock().lock();
                try {
                    System.out.println(System.currentTimeMillis() + " Write lock is acquired " + Thread.currentThread().getName());
                    Utils.sleepSeconds(3);
                } finally {
                    System.out.println(System.currentTimeMillis() + " Write lock is released " + Thread.currentThread().getName());
                    lock.writeLock().unlock();
                }
            });
        }

        executor.shutdown();
    }
}
