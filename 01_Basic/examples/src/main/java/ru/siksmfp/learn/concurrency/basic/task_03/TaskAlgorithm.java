package ru.siksmfp.learn.concurrency.basic.task_03;

import java.util.List;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class TaskAlgorithm {
    public static void main(String[] args) {
        List<Task> tasks = TaskHolder.getTasks();

        TaskExecutor taskExecutor = new TaskExecutor();
        long startTime = System.nanoTime();
        tasks.forEach(taskExecutor::executeTask);
        long finishTime = System.nanoTime();

        System.out.println("Current time " + SECONDS.convert(finishTime - startTime, NANOSECONDS) + " seconds"); //improve it (make it smaller)
    }
}
