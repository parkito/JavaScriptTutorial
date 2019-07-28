package ru.siksmfp.learn.concurrency.basic.example_05;

import ru.siksmfp.learn.concurrency.basic.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Atomicity {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        UnsafeSharedResource unsafeSharedResource = new UnsafeSharedResource();
        for (int i = 0; i < 30; i++) {
            executor.execute(() -> System.out.println(unsafeSharedResource.increment() + " " + Thread.currentThread().getName()));
        }

        Utils.sleepSeconds(8);
        System.out.println("Unsafe result " + unsafeSharedResource.getValue());

        VolatileSharedResource volatileSharedResource = new VolatileSharedResource();
        for (int i = 0; i < 30; i++) {
            executor.execute(() -> System.out.println(volatileSharedResource.increment() + " " + Thread.currentThread().getName()));
        }

        Utils.sleepSeconds(8);
        System.out.println("Volatile result " + volatileSharedResource.getValue());

        AtomicSharedResource atomicSharedResource = new AtomicSharedResource();
        for (int i = 0; i < 30; i++) {
            executor.execute(() -> System.out.println(atomicSharedResource.increment() + " " + Thread.currentThread().getName()));
        }

        Utils.sleepSeconds(8);
        System.out.println("Atomic result " + atomicSharedResource.getValue());

        executor.shutdown();

    }
}
