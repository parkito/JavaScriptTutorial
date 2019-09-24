package ru.siksmfp.learn.concurrency.intermediate.collection.concurrent_skip_list_map;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SkipListMap {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();

        executorService.submit(() -> {
            System.out.println("Populating is started");
            for (int i = 0; i < 10; i++) {
                System.out.println("Put " + i);
                map.put(i, String.valueOf(i));
                Utils.sleepSeconds(2);
            }
        });

        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                Utils.sleepSeconds(3);
                System.out.println(map.keySet());
            }
        });

        executorService.shutdown();

    }
}
