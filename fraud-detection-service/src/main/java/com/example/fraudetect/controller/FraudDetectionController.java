package com.example.fraudetect.controller;

import com.example.fraudetect.entity.LoanStatus;
import com.example.fraudetect.service.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud")
@RequiredArgsConstructor
@Slf4j
public class FraudDetectionController {

    private final FraudDetectionService fraudDetectionService;

    @GetMapping("/check/{customerId}")
    public LoanStatus checkForFraud(@PathVariable int customerId) {
        log.info("Checking for fraud for customer id: {}", customerId);
        return fraudDetectionService.checkForFraud(customerId);
    }
}
