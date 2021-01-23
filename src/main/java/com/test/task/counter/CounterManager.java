package com.test.task.counter;

import com.test.task.counter.executor.CounterExecutor;
import com.test.task.counter.executor.impl.DecreaseExecutor;
import com.test.task.counter.executor.impl.IncreaseExecutor;
import com.test.task.service.IncrementThreadRequestService;
import com.test.task.service.SetCounterRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class CounterManager {
    private final AtomicInteger counter;
    private final CounterExecutor decrementExecutor;
    private final CounterExecutor incrementExecutor;
    private final IncrementThreadRequestService incrementThreadRequestEntityService;
    private final SetCounterRequestService setCounterRequestService;

    @Autowired
    public CounterManager(
            @Qualifier("supplierCounterExecutor") ThreadPoolTaskExecutor supplierExecutor,
            @Qualifier("producerCounterExecutor") ThreadPoolTaskExecutor producerExecutor,
            @Value("${counter.init-value}") Integer counterInitValue,
            @Value("${counter.min-border}") Integer minCounterBorder,
            @Value("${counter.max-border}") Integer maxCounterBorder,
            IncrementThreadRequestService incrementThreadRequestEntityService,
            SetCounterRequestService setCounterRequestService) {
        this.counter = new AtomicInteger();
        this.counter.set(counterInitValue);
        this.incrementExecutor = new IncreaseExecutor(producerExecutor, counter, minCounterBorder, maxCounterBorder);
        this.decrementExecutor = new DecreaseExecutor(supplierExecutor, counter, minCounterBorder, maxCounterBorder);
        this.setCounterRequestService = setCounterRequestService;
        this.incrementThreadRequestEntityService = incrementThreadRequestEntityService;
    }

    public void increaseThreadCount(int consumerCount, int producerCount) {
        incrementThreadRequestEntityService.saveRequest(consumerCount, producerCount);
        incrementExecutor.increaseWorkerThread(producerCount);
        decrementExecutor.increaseWorkerThread(consumerCount);
        incrementExecutor.startJob(producerCount);
        decrementExecutor.startJob(consumerCount);
    }

    public void setCounterValue(int value) {
        counter.set(value);

        if (incrementExecutor.isFinish()) {
            incrementExecutor.restartAllThreads();
        }

        if (decrementExecutor.isFinish()) {
            decrementExecutor.restartAllThreads();
        }

        setCounterRequestService.save(value);
    }

}
