package ru.siksmfp.learn.concurrency.basic.task_03;

import ru.siksmfp.learn.concurrency.basic.Utils;

import static ru.siksmfp.learn.concurrency.basic.task_03.Status.FINISHED;
import static ru.siksmfp.learn.concurrency.basic.task_03.Status.STARTED;

public class TaskExecutor {

    public void executeTask(Task task) {
        task.setStatus(STARTED);
        Utils.sleepSeconds(task.getTimeToComplete());
        task.setStatus(FINISHED);
        System.out.println("Executed task " + task);
    }
}
