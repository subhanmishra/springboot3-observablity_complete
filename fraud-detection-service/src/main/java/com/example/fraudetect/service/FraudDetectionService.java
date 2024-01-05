package com.example.fraudetect.service;

import com.example.fraudetect.entity.LoanStatus;
import com.example.fraudetect.repository.FraudRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudDetectionService {

    private final FraudRecordRepository fraudRecordRepository;

    public LoanStatus checkForFraud(int customerId) {
        return fraudRecordRepository.existsByCustomerId(customerId) ? LoanStatus.REJECTED : LoanStatus.APPROVED;
    }
}
