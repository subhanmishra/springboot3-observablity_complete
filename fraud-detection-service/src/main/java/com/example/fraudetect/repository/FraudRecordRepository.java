package com.example.fraudetect.repository;

import com.example.fraudetect.model.FraudRecord;
import org.springframework.data.repository.ListCrudRepository;

public interface FraudRecordRepository extends ListCrudRepository<FraudRecord, Long> {

    boolean existsByCustomerId(int customerId);
}
