package ru.siksmfp.learn.concurrency.basic.example_09;

import ru.siksmfp.learn.concurrency.basic.Utils;

public class Starvation {

    private static class Worker {

        private String workerName;

        public Worker(String workerName) {
            this.workerName = workerName;
        }

        public synchronized void work() {
            System.out.println(workerName + " started working");
            for (int i = 0; i < 10; i++) {
                System.out.print("Step  " + i + " finished;");
                Utils.sleepSeconds(1);//heavy operation
            }
            System.out.println();
            System.out.println(workerName + " finished working");
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker("1");

        for (int i = 0; i < 10; i++) {
            new Thread(worker::work).start();
        }

        //correct
        for (int i = 0; i < 10; i++) {
            Worker currentWorker = new Worker(String.valueOf(i));
            new Thread(currentWorker::work).start();
        }
    }
}
