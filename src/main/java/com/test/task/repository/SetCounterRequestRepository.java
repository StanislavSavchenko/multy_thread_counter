package com.test.task.repository;

import com.test.task.entity.SetCounterRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface SetCounterRequestRepository extends CrudRepository<SetCounterRequestEntity, Long> {
}
