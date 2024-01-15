package com.example.fraudetect.service;

import com.example.fraudetect.model.LoanStatus;
import com.example.fraudetect.repository.FraudRecordRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Observed
public class FraudDetectionService {

    private final FraudRecordRepository fraudRecordRepository;
    @Transactional(readOnly = true)
    public LoanStatus checkForFraud(int customerId) {
//        throw new RuntimeException("Test Exception for checking Circuit Breaker");
        var status = fraudRecordRepository.existsByCustomerId(customerId) ? LoanStatus.REJECTED : LoanStatus.APPROVED;
        log.info("Fraud chk result for customerId {} is: {}", customerId, status);
        return status;
    }
}
