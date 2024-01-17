package com.example.fraudetect.repository;

import com.example.fraudetect.model.Fraud;
import org.springframework.data.repository.ListCrudRepository;

public interface FraudRecordRepository extends ListCrudRepository<Fraud, Long> {

    boolean existsByCustomerId(int customerId);
}
