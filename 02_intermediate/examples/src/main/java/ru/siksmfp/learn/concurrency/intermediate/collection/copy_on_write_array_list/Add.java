package ru.siksmfp.learn.concurrency.intermediate.collection.copy_on_write_array_list;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Add {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                Utils.sleepSeconds(1);
                list.add(i); //method synchronized internally
            }
        });

        executorService.execute(()->{
            Utils.sleepSeconds(7);
            list.remove(0); //list modification is allowed
        });

        for (int i = 0; i < 10; i++) {
            Utils.sleepSeconds(3);
            executorService.submit(() -> {
                for (Integer integer : list) {//for each new iterator new backed array is copied
                    System.out.print(integer + " ");
                }
                System.out.println("----");
            });
        }

        executorService.shutdown();
    }
}
