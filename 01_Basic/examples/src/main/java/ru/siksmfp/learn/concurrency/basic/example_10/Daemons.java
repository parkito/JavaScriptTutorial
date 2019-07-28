package ru.siksmfp.learn.concurrency.basic.example_10;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class Daemons {

    private static class DaemonThread extends Thread {
        public DaemonThread() {
            setDaemon(true);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Working in background step " + i);
                    Utils.sleepSeconds(1);
                } finally {
                    System.out.println("Finally for " + i + " step");
                }
            }
        }
    }

    public static void main(String[] args) {
        new DaemonThread().start();
        Utils.sleepSeconds(3);
    }
}
