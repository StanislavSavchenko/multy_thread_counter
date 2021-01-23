package com.test.task.counter.executor.impl;

import com.test.task.counter.executor.CounterExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DecreaseExecutor extends CounterExecutor {

    public DecreaseExecutor(ThreadPoolTaskExecutor executor, AtomicInteger counter, int minCounterBorder, int maxCounterBorder) {
        super(executor, minCounterBorder, maxCounterBorder, counter);
    }

    @Override
    protected Runnable getCounterRunnable(AtomicInteger counter, int minCounterBorder, int maxCounterBorder) {
        return () -> {
            while (counter.get() < maxCounterBorder && counter.get() != minCounterBorder) {
                int current = counter.get();
                int prev = current - 1;
                counter.compareAndSet(current, prev);
                log.info("Thread name = {}, counter value = {}", Thread.currentThread().getName(), counter.get());
            }
        };
    }
}
