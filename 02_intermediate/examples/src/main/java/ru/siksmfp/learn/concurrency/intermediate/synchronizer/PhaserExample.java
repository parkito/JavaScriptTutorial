package ru.siksmfp.learn.concurrency.intermediate.synchronizer;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.Phaser;

// Улучшенная реализация барьера для синхронизации потоков,
// которая совмещает в себе функционал CyclicBarrier и CountDownLatch,
// вбирая в себя самое лучшее из них.
// Так, количество потоков жестко не задано и может динамически меняться.
// Класс может повторно переиспользоваться и сообщать о готовности потока без его блокировки.
public class PhaserExample {

    public static void main(String[] args) {
        Phaser ph = new Phaser(1);
        int curPhase;
        curPhase = ph.getPhase();
        System.out.println("Phase in Main " + curPhase + " started");
        // Threads for first phase
        new FileReaderThread("thread-1", "file-1", ph);
        new FileReaderThread("thread-2", "file-2", ph);
        new FileReaderThread("thread-3", "file-3", ph);
        //For main thread
        ph.arriveAndAwaitAdvance();
        System.out.println("New phase " + ph.getPhase() + " started");
        // Threads for second phase
        new QueryThread("thread-1", 40, ph);
        new QueryThread("thread-2", 40, ph);
        curPhase = ph.getPhase();
        ph.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " completed");
        // deregistering the main thread
        ph.arriveAndDeregister();
    }
}

class FileReaderThread implements Runnable {
    private String threadName;
    private String fileName;
    private Phaser ph;

    FileReaderThread(String threadName, String fileName, Phaser ph) {
        this.threadName = threadName;
        this.fileName = fileName;
        this.ph = ph;
        ph.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("This is phase " + ph.getPhase());

        Utils.sleepSeconds(1);
        System.out.println("Reading file " + fileName + " thread "
                + threadName + " parsing and storing to DB ");
        // Using await and advance so that all thread wait here
        ph.arriveAndAwaitAdvance();
        ph.arriveAndDeregister();
    }
}

class QueryThread implements Runnable {
    private String threadName;
    private int param;
    private Phaser ph;

    QueryThread(String threadName, int param, Phaser ph) {
        this.threadName = threadName;
        this.param = param;
        this.ph = ph;
        ph.register();
        new Thread(this).start();
    }

    @Override
    public void run() {

        System.out.println("This is phase " + ph.getPhase());
        System.out.println("Querying DB using param " + param
                + " Thread " + threadName);
        ph.arriveAndAwaitAdvance();
        System.out.println("Threads finished");
        ph.arriveAndDeregister();
    }
}