package ru.siksmfp.learn.concurrency.intermediate.lock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {

    @Test
    public void should_unblock_parked_thread() throws InterruptedException {
        List<Integer> iteratedNumbers = new ArrayList<>();
        Thread thread1 = new Thread(() -> {
            int i = 0;
            // park() blocks thread invoking this method
            LockSupport.park();
            while (true) {
                try {
                    Thread.sleep(1_000L);
                    iteratedNumbers.add(i);
                    i++;
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 got InterruptedException");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(2_600L);
            } catch (InterruptedException e) {
                System.out.println("Thread 2 got InterruptedException");
                Thread.currentThread().interrupt();
            }
            // unpark(Thread) releases thread specified
            // in the parameter
            LockSupport.unpark(thread1);
        });
        thread2.start();

        Thread.sleep(5_000L);
        thread1.interrupt();

        Assertions.assertEquals(2, iteratedNumbers.size());
        // Only 2 numbers are expected:
        // * thread1 blocks before starting the iteration
        // * thread2 wakes up after ~3 seconds and releases blocked thread1
        // * from 5 seconds allocated to execution, thread1 has only 2
        //   seconds to execute and since the sleep between iterations
        //   is 1 second, it should make only 2 iterations
        Assertions.assertTrue(iteratedNumbers.contains(1));
        Assertions.assertTrue(iteratedNumbers.contains(0));
    }

    @Test
    public void should_block_thread_with_deadline() throws InterruptedException {
        List<Integer> iteratedNumbers = new ArrayList<>();
        Thread thread1 = new Thread(() -> {
            int i = 0;
            // park() blocks thread invoking this method
            long lockReleaseTimestamp = System.currentTimeMillis() + 2_600L;
            LockSupport.parkUntil(lockReleaseTimestamp);
            while (true) {
                try {
                    Thread.sleep(1_000L);
                    iteratedNumbers.add(i);
                    i++;
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 got InterruptedException");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread1.start();

        Thread.sleep(5_000L);
        thread1.interrupt();

        Assertions.assertEquals(2, iteratedNumbers.size());
        // Only 2 numbers are expected because lock is held during ~3 seconds:
        // * thread1 blocks before starting the iteration during ~3 seconds
        // * from 5 seconds allocated to execution, thread1 has only 2 seconds
        //   of execution time and since the sleep between iterations is 1 second,
        //   it should make only 2 iterations
        Assertions.assertTrue(iteratedNumbers.contains(1));
        Assertions.assertTrue(iteratedNumbers.contains(0));
    }

    @Test
    public void should_prove_that_blocker_is_not_an_exclusive_lock() throws InterruptedException {
        Object lock = new Object();
        boolean[] blocks = new boolean[2];
        Thread thread1 = new Thread(() -> {
            LockSupport.park(lock);
            blocks[0] = true;
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            LockSupport.park(lock);
            blocks[1] = true;
        });
        thread2.start();

        Thread.sleep(2_000L);

        // Both threads stopped with the same blocker object (Object lock)
        // It shows that blocker can't work as an exclusive lock mechanism
        Object blockerThread1 = LockSupport.getBlocker(thread1);
        Object blockerThread2 = LockSupport.getBlocker(thread2);

        Assertions.assertEquals(lock, blockerThread1);
        Assertions.assertEquals(lock, blockerThread2);
        Assertions.assertFalse(blocks[0]);
        Assertions.assertFalse(blocks[1]);
    }

    @Test
    public void should_implement_locking_mechanism_with_blocker() throws InterruptedException {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            LockSupport.parkUntil(lock, System.currentTimeMillis() + 3_000L);
        });
        thread1.start();

        long timeBeforeLockAcquire = System.currentTimeMillis();

        // Give some guarantee to thread1 to acquire lock
        Thread.sleep(10L);

        // Try to lock current thread as long as
        // thread1 doesn't release its lock - let's suppose
        // that thread1 is making some job needed by current thread
        while (LockSupport.getBlocker(thread1) != null) {
        }
        LockSupport.parkUntil(lock, System.currentTimeMillis() + 1_000L);
        long timeAfterLockRelease = System.currentTimeMillis();
        long duration = timeAfterLockRelease - timeBeforeLockAcquire;

        // Duration should be ~4 seconds because of 3 seconds of lock
        // acquired by thread1 and 1-second blocking of current thread
        Assertions.assertTrue(duration >= 4000);
        Assertions.assertTrue(duration <= 4500);
    }
}
