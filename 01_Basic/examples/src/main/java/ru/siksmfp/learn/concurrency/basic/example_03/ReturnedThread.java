package ru.siksmfp.learn.concurrency.basic.example_03;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class ReturnedThread implements Runnable {

    private String string;

    public String getString() {
        return string;
    }

    @Override
    public void run() {
        Utils.sleepSeconds(3);
        string = "Result";
    }
}
