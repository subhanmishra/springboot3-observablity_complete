package com.example.loans.client;

import com.example.loans.entity.LoanStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;


public interface FraudDetectionClient {

    @GetExchange("/fraud/check/{customerId}")
    LoanStatus evaluateLoan(@PathVariable int customerId);

}
