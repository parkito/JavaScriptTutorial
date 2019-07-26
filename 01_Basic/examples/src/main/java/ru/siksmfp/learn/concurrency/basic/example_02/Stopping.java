package ru.siksmfp.learn.concurrency.basic.example_02;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class Stopping {
    public static void main(String[] args) {
        UnsafeStoppableThread unsafeStoppable = new UnsafeStoppableThread();
        Thread stoppableThread = new Thread(unsafeStoppable);
        stoppableThread.start();

        Utils.sleepSeconds(1);
        unsafeStoppable.doStop();


        Thread interruptableThread = new Thread(new InterruptableThread());
        interruptableThread.start();

        interruptableThread.interrupt();
    }
}
