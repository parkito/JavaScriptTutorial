package ru.siksmfp.learn.concurrency.basic.example_04;

import ru.siksmfp.learn.concurrency.basic.example_01.UnsafePingPong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Safety {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        UnsafePingPong unsafePingPong = new UnsafePingPong();
        for (int i = 0; i < 100; i++) {
            executor.execute(unsafePingPong::print);
        }

        SynchronizedPingPong synchronizedPingPong = new SynchronizedPingPong();
        for (int i = 0; i < 100; i++) {
            executor.execute(synchronizedPingPong::fullSyncPrint);
        }
    }
}
