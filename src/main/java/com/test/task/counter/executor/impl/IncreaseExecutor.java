package com.test.task.counter.executor.impl;

import com.test.task.counter.executor.CounterExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class IncreaseExecutor extends CounterExecutor {

    public IncreaseExecutor(ThreadPoolTaskExecutor executor, AtomicInteger counter, int minCounterBorder, int maxCounterBorder) {
        super(executor, minCounterBorder, maxCounterBorder, counter);
    }

    @Override
    protected Runnable getCounterRunnable(AtomicInteger counter, int minCounterBorder, int maxCounterBorder) {
        return () -> {
            while (counter.get() < maxCounterBorder && counter.get() != minCounterBorder) {
                int current = counter.get();
                int next = current + 1;
                counter.compareAndSet(current, next);
                log.info("Thread name = {}, counter value = {}", Thread.currentThread().getName(), counter.get());
            }
        };
    }
}
