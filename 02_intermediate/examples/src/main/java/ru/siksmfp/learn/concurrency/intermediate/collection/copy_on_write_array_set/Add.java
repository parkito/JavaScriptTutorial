package ru.siksmfp.learn.concurrency.intermediate.collection.copy_on_write_array_set;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Add {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CopyOnWriteArraySet<Integer> list = new CopyOnWriteArraySet<>();

        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                Utils.sleepSeconds(1);
                list.add(i); //method synchronized internally
            }
        });

        for (int i = 0; i < 10; i++) {
            Utils.sleepSeconds(1);
            executorService.submit(() -> {
                for (Integer integer : list) {
                    System.out.print(integer + " ");
                }
                System.out.println("----");
            });
        }

        executorService.shutdown();
    }
}
