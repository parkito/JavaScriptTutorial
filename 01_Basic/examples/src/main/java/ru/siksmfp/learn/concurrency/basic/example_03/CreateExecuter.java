package ru.siksmfp.learn.concurrency.basic.example_03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateExecuter {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> System.out.println(Thread.currentThread().getName()));

        System.out.println("Main thread");
        executor.shutdown();
    }
}
