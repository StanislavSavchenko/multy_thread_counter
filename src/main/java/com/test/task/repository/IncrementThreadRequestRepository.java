package com.test.task.repository;

import com.test.task.entity.IncrementThreadRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface IncrementThreadRequestRepository extends CrudRepository<IncrementThreadRequestEntity, Long> {
}
