package ru.siksmfp.learn.concurrency.intermediate.queue.blocking.blocking_deque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

// Интерфейс, описывающий дополнительные методы для двунаправленной блокирующей очереди.
// Данные можно вставлять и вытаскивать с двух сторон очереди.
public class BlockingDequeExample {

    public static void main(String[] args) {

        // Двунаправленная блокирующая очередь на связанных нодах,
        // реализованная как простой двунаправленный список с одним локом.
        // Размер очереди задается через конструктор и по умолчанию равен Integer.MAX_VALUE
        BlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>();
    }
}
