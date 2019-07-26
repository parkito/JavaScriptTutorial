package ru.siksmfp.learn.concurrency.basic.example_03;

import ru.siksmfp.learn.concurrency.basic.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReturnObject {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> callableTask = () -> {
            Utils.sleepSeconds(2);
            return "Task's execution";
        };

        Future<String> submit = executor.submit(callableTask);

        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
