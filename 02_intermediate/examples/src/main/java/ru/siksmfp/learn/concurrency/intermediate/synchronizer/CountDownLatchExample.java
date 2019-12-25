package ru.siksmfp.learn.concurrency.intermediate.synchronizer;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

//Await will block until the counter reaches 0
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<String> jobStatus = new CopyOnWriteArrayList<>();
        System.out.println("Starting jobs");
        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        Utils.sleepSeconds(3);
                        jobStatus.add(Thread.currentThread().getName() + " done");
                        countDownLatch.countDown();
                    }
            ).start();
        }

        countDownLatch.await();
        System.out.println(jobStatus);

    }
}
