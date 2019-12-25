package ru.siksmfp.learn.concurrency.intermediate.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Del implements Delayed {

    private int delay;

    public Del(int delay) {
        this.delay = delay;
    }

    /**
     * Returns the remaining delay associated with this object, in the
     * given time unit.
     *
     * @param unit the time unit
     * @return the remaining delay; zero or negative values indicate
     * that the delay has already elapsed
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return - delay;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
