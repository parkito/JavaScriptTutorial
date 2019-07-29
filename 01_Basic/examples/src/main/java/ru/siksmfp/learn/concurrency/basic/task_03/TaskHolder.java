package ru.siksmfp.learn.concurrency.basic.task_03;

import java.util.ArrayList;
import java.util.List;

public class TaskHolder {

    public static List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>(100);
        for (int i = 0; i < 7; i++) {
            tasks.add(new Task(i));
        }
        return tasks;
    }
}
