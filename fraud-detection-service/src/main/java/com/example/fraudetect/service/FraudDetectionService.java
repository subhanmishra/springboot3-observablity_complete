package com.example.fraudetect.service;

import com.example.fraudetect.model.LoanStatus;
import com.example.fraudetect.repository.FraudRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudDetectionService {

    private final FraudRecordRepository fraudRecordRepository;
    @Transactional(readOnly = true)
    public LoanStatus checkForFraud(int customerId) {
        var status = fraudRecordRepository.existsByCustomerId(customerId) ? LoanStatus.REJECTED : LoanStatus.APPROVED;
        log.info("Fraud chk result for customerId {} is: {}", customerId, status);
        return status;
    }
}
