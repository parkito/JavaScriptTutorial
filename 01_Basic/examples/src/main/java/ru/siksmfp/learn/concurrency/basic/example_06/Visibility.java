package ru.siksmfp.learn.concurrency.basic.example_06;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class Visibility {
    public static void main(String[] args) {
        UnsafeLoopThread unsafeLoopThread = new UnsafeLoopThread();

        new Thread(unsafeLoopThread).start();

        Utils.sleepSeconds(1);
        unsafeLoopThread.isStopped = true;

        VolatileLoopThread volatileLoopThread = new VolatileLoopThread();

        new Thread(volatileLoopThread).start();

        Utils.sleepSeconds(1);
        volatileLoopThread.isStopped = true;

        AtomicLoopThread atomicLoopThread = new AtomicLoopThread();


//        SynchronizedLoopThread synchronizedLoopThread = new SynchronizedLoopThread();
//
//        new Thread(synchronizedLoopThread).start();
//
//        Utils.sleepSeconds(1);
//        synchronizedLoopThread.isStopped = true;
    }
}
