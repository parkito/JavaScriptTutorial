package ru.siksmfp.learn.concurrency.intermediate.synchronizer;

import ru.siksmfp.learn.concurrency.intermediate.Utils;

import java.util.concurrent.Exchanger;

// Represents a kind of rendezvous point where two threads can exchange objects
public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        new Thread(() -> {
            try {
                System.out.println(exchanger.exchange("A"));//blocking until receive
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Utils.sleepSeconds(3);

        new Thread(() -> {
            try {
                System.out.println(exchanger.exchange("B"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
