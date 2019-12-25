package ru.siksmfp.learn.concurrency.intermediate.synchronizer;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// A CyclicBarrier is a synchronizer that allows a set of threads to wait for each other
// to reach a common execution point, also called a barrier.
public class CyclicBarrierExample {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        try {
                            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " is waiting for other thread are constructed");
                            Utils.sleepSeconds(2);
                            cyclicBarrier.await();
                            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " constructed");
                        } catch (InterruptedException | BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
    }
}
