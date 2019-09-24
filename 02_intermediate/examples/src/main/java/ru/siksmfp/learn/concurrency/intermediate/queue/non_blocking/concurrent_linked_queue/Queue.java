package ru.siksmfp.learn.concurrency.intermediate.queue.non_blocking.concurrent_linked_queue;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//В имплементации используется wait-free алгоритм от Michael & Scott,
// адаптированный для работы с garbage collector'ом.
// Этот алгоритм довольно эффективен и, что самое важное,
// очень быстр, т.к. построен на CAS.
// Метод size() может работать долго, т.ч. лучше постоянно его не дергать.
public class Queue {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentLinkedQueue<Integer> integers = new ConcurrentLinkedQueue<>();

        executorService.execute(() -> {
            Utils.sleepSeconds(1);
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                integers.add(i);
            }
            System.out.println("Done");
        });

        executorService.execute(() -> {
            for (int i = 300; i > 200; i--) {
                integers.add(i);
                Utils.sleepSeconds(1);
            }
        });

        executorService.execute(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                System.out.println("Element " + integers.poll());
            }
        });

        executorService.shutdown();
    }
}
