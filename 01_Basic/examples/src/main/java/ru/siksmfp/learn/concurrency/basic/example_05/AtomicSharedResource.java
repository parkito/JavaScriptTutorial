package ru.siksmfp.learn.concurrency.basic.example_05;

import ru.siksmfp.learn.concurrency.basic.Utils;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicSharedResource {

    private AtomicLong atomicLong = new AtomicLong();

    public long getValue() {
        return atomicLong.longValue();
    }

    public long increment() {
        Utils.sleepSeconds(1);
        return atomicLong.addAndGet(1);
    }
}
