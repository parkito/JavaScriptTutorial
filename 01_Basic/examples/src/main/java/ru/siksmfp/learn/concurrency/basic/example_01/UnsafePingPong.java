package ru.siksmfp.learn.concurrency.basic.example_01;

public class UnsafePingPong {
    private int count;

    public void print() {
        if (count % 2 == 0) {
            System.out.print("ping ");
            System.out.print(count);
        } else {
            System.out.print("pong ");
            System.out.print(count);
        }
        System.out.print(" ");
        System.out.println(Thread.currentThread().getName());
        count++;
    }
}