package com.test.task.counter.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class CounterExecutor {
    private final ThreadPoolTaskExecutor executor;
    private final int minCounterBorder;
    private final int maxCounterBorder;
    private AtomicInteger counter;

    public CounterExecutor(ThreadPoolTaskExecutor executor, int minCounterBorder, int maxCounterBorder, AtomicInteger counter) {
        this.executor = executor;
        this.minCounterBorder = minCounterBorder;
        this.maxCounterBorder = maxCounterBorder;
        this.counter = counter;
    }

    public boolean isFinish() {
        return executor.getActiveCount() == 0;
    }

    public void increaseWorkerThread(int threadCount) {
        executor.setCorePoolSize(executor.getPoolSize() + threadCount);
    }

    public void restartAllThreads() {
        startJob(executor.getCorePoolSize());
    }

    public void startJob(int jobCount) {
        for (int i = 0; i < jobCount; i++) {
            executor.execute(getCounterRunnable(counter, minCounterBorder, maxCounterBorder));
        }
    }

    protected abstract Runnable getCounterRunnable(AtomicInteger counter, int minCounterBorder, int maxCounterBorder);

}
