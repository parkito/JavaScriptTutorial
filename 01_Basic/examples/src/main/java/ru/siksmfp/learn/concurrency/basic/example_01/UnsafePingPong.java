package ru.siksmfp.learn.concurrency.basic.example_01;

public class UnsafePingPong {
    private int count;

    public void print() {
        if (count % 2 == 0) {
            System.out.println("ping " + count + " " + Thread.currentThread().getName());
        } else {
            System.out.println("pong " + count + " " + Thread.currentThread().getName());
        }
        count++;
    }
}