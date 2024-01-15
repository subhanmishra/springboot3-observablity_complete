package com.example.loans.service;

import com.example.loans.client.FraudDetectionClient;
import com.example.loans.dto.LoanDto;
import com.example.loans.model.Loan;
import com.example.loans.model.LoanStatus;
import com.example.loans.repository.LoanRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Observed
public class LoanService {

    private final FraudDetectionClient fraudDetectionClient;
    private final LoanRepository loanRepository;
    private final RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public List<LoanDto> listAllLoans() {
        var loans = loanRepository.findAll()
                .stream()
                .map(LoanDto::from)
                .toList();
        log.info("Size of fetched loans is: {}", loans.size());
        return loans;
    }

    @Transactional
    @CircuitBreaker(name = "fraudService", fallbackMethod = "fraudServiceFallback")
    public String applyLoan(LoanDto loanDto) {
        var loan = Loan.from(loanDto);
        log.info("Calling Fraud Detection Service for customer id: {}", loan.getCustomerId());
        //LoanStatus loanStatus = restTemplate.getForObject("http://localhost:8081/fraud/check/" + loan.getCustomerId(), LoanStatus.class);;


        LoanStatus loanStatus = fraudDetectionClient.evaluateLoan(loan.getCustomerId());
        log.info("Fraud Detection Service response: {}", loanStatus);
        loan.setLoanStatus(loanStatus);
        if (loanStatus.equals(LoanStatus.APPROVED)) {
            loan.setLoanId(UUID.randomUUID().toString());
            loanRepository.save(loan);
            return "Loan applied successfully";
        }
        return "Sorry! Your loan was not approved";
    }

    /**
     * Fallback method for 4xx exceptions, just percolate the exception back.
     */
    private String fraudServiceFallback(LoanDto loanDto, HttpClientErrorException e) {
        log.error("Fallback-----: Fraud Check for customer id: {} resulted in a client exception with status: {} and message: {}", loanDto.customerId(), e.getStatusCode(), e.getMessage());
        throw e;
    }

    /**
     * Fallback method for all other exception types.
     */
    private String fraudServiceFallback(LoanDto loanDto, Exception ex) throws Exception {
        log.error("Fallback------: Fraud Check for customer id: {} resulted in exception with cause: {}", loanDto.customerId(), ex.getMessage());
        throw ex;
    }
}
