package ru.siksmfp.learn.concurrency.basic.task_03;

import static ru.siksmfp.learn.concurrency.basic.task_03.Status.NOT_STARTED;

public class Task {
    private int timeToComplete;
    private Status status;

    public int getTimeToComplete() {
        return timeToComplete;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task(int timeToComplete) {
        this.timeToComplete = timeToComplete;
        status = NOT_STARTED;
    }

    @Override
    public String toString() {
        return "Task{" +
                "timeToComplete=" + timeToComplete +
                ", status=" + status +
                '}';
    }
}
