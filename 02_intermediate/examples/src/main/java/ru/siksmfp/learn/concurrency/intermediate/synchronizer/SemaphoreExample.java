package ru.siksmfp.learn.concurrency.intermediate.synchronizer;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.Semaphore;

//Critical section is available only for n threads
public class SemaphoreExample {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " try acquire ");
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " acquired");
                        Utils.sleepSeconds(3);
                        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " releasing");

                        semaphore.release();
                        System.out.println(System.currentTimeMillis() + " Available " + semaphore.availablePermits());
                    }
            ).start();

        }
    }
}
