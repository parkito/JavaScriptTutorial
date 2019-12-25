package ru.siksmfp.learn.concurrency.intermediate.queue.blocking.delay_queue;

import ru.siksmfp.learn.concurrency.intermediate.Utils;
import ru.siksmfp.learn.concurrency.intermediate.queue.Del;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Довольно специфичный класс, который позволяет вытаскивать элементы из очереди
// только по прошествии некоторой задержки,
// определенной в каждом элементе через метод getDelay интерфейса Delayed.
public class Add {
    private static boolean done = false;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        BlockingQueue<Del> queue = new DelayQueue<>();

        executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Adding " + i + " rest " + queue.size());
                queue.add(new Del(i)); //IllegalStateException is full
            }

            Utils.sleepSeconds(5);
            done = true;

        });

        executorService.submit(() -> {
            for (int i = 0; i < 100; i++) {
                Utils.sleepSeconds(1);
                Del poll = queue.poll();
                if (poll == null) {
                    System.out.println("Waiting");
                } else {
                    System.out.println("Delay is finished for " + poll.getDelay());
                }
                //Retrieves and removes the head of this queue, or returns {@code null}
                //  if this queue has no elements with an expired delay.
            }
        });

        executorService.shutdown();
    }
}

