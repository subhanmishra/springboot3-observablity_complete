package com.example.fraudetect.service;

import com.example.fraudetect.dto.FraudDto;
import com.example.fraudetect.exception.CustomerExistsException;
import com.example.fraudetect.model.Fraud;
import com.example.fraudetect.model.LoanStatus;
import com.example.fraudetect.repository.FraudRecordRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    @Transactional
    public String createFraudRecord(FraudDto fraudDto){

        if(fraudRecordRepository.existsByCustomerId(fraudDto.customerId())) {
            log.info("customerId {} already exists in Fraud Database", fraudDto.customerId());
            throw new CustomerExistsException("Customer with id: " + fraudDto.customerId() + " already exists in Fraud DB.");
        }

        var dto = Fraud.from(fraudDto);
        dto.setFraudRecordId(UUID.randomUUID().toString());
        fraudRecordRepository.save(dto);
        log.info("Record successfully inserted in Fraud Database for customerId {}.", fraudDto.customerId());
        return "Fraud Record successfully inserted for customerId: " + fraudDto.customerId();
    }
}
