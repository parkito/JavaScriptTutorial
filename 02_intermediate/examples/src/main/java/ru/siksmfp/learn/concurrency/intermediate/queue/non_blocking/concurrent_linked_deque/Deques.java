package ru.siksmfp.learn.concurrency.intermediate.queue.non_blocking.concurrent_linked_deque;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//ConcurrentLinkedDeque<E> — Deque расшифровывается как Double ended queue и читается как «Deck».
// Это означает, что данные можно добавлять и вытаскивать с обоих сторон.
// Соответственно, класс поддерживает оба режима работы:
// FIFO (First In First Out) и LIFO (Last In First Out).
// На практике, ConcurrentLinkedDeque стоит использовать только,
// если обязательно нужно LIFO, т.к. за счет двунаправленности нод
// данный класс проигрывает по производительности на 40% по сравнению с ConcurrentLinkedQueue.
public class Deques {

    public static void main(String[] args) {
        Deque<Integer> clDQue = new ConcurrentLinkedDeque<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // Calling one producer
        executor.execute(new ProdTask(clDQue));
        // Calling two consumers
        executor.execute(new ConTask(clDQue));
        executor.execute(new ConTask(clDQue));
        executor.shutdown();
    }
}

class ProdTask implements Runnable {
    private Deque<Integer> clDQue;

    ProdTask(Deque<Integer> clDQue) {
        this.clDQue = clDQue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            clDQue.add(i);
        }
    }
}

class ConTask implements Runnable {
    private Deque<Integer> clDQue;

    ConTask(Deque<Integer> clDQue) {
        this.clDQue = clDQue;
    }

    @Override
    public void run() {
        Integer value;
        while ((value = clDQue.pollFirst()) != null) {
            System.out.println("Consumer recd - " + value);
        }
    }
}
