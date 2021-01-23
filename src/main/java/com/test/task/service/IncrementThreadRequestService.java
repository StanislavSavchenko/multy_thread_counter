package com.test.task.service;

import com.test.task.entity.IncrementThreadRequestEntity;
import com.test.task.repository.IncrementThreadRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IncrementThreadRequestService {
    private final IncrementThreadRequestRepository incrementThreadRequestEntityRepository;
    private final TimeService timeService;

    @Autowired
    public IncrementThreadRequestService(IncrementThreadRequestRepository incrementThreadRequestEntityRepository, TimeService timeService) {
        this.incrementThreadRequestEntityRepository = incrementThreadRequestEntityRepository;
        this.timeService = timeService;
    }

    public IncrementThreadRequestEntity saveRequest(int consumerCount, int producerCount) {
        IncrementThreadRequestEntity incrementThreadRequestEntity = new IncrementThreadRequestEntity();
        incrementThreadRequestEntity.setConsumer(consumerCount);
        incrementThreadRequestEntity.setProducer(producerCount);
        incrementThreadRequestEntity.setRequestDt(timeService.getInstantNow());
        incrementThreadRequestEntity = incrementThreadRequestEntityRepository.save(incrementThreadRequestEntity);
        return incrementThreadRequestEntity;
    }

}
