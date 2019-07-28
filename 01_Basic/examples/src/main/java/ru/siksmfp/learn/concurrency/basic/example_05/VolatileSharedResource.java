package ru.siksmfp.learn.concurrency.basic.example_05;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class VolatileSharedResource {

    private volatile long value;

    public long getValue() {
        return value;
    }

    public long increment() {
        Utils.sleepSeconds(1);
        return value++;
    }
}
