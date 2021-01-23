package com.test.task.service;

import com.test.task.entity.SetCounterRequestEntity;
import com.test.task.repository.SetCounterRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetCounterRequestService {
    private final SetCounterRequestRepository setCounterRequestRepository;
    private final TimeService timeService;

    @Autowired
    public SetCounterRequestService(SetCounterRequestRepository setCounterRequestRepository, TimeService timeService) {
        this.setCounterRequestRepository = setCounterRequestRepository;
        this.timeService = timeService;
    }

    public SetCounterRequestEntity save(Integer counter) {
        SetCounterRequestEntity setCounterRequestEntity = new SetCounterRequestEntity();
        setCounterRequestEntity.setCounter(counter);
        setCounterRequestEntity.setRequestDt(timeService.getInstantNow());
        return setCounterRequestRepository.save(setCounterRequestEntity);
    }

}
