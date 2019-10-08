package ru.siksmfp.learn.concurrency.intermediate.queue.blocking.array_locking_queue;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Класс блокирующей очереди, построенный на классическом кольцевом буфере.
// Помимо размера очереди, доступна возможность управлять «честностью» блокировок.
// Если fair=false (по умолчанию), то очередность работы потоков не гарантируется.
public class Add {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Utils.sleepSeconds(1);
                    System.out.println("Adding " + i + " rest " + queue.size());
                    queue.add(String.valueOf(i)); //IllegalStateException is full
                } catch (IllegalStateException ex) {
                    ex.printStackTrace();
                }
            }
        });

        executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                Utils.sleepSeconds(3);
                System.out.println("Getting " + queue.poll());//null  if is empty
            }
        });

        executorService.shutdown();
    }
}
