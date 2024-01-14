package com.example.loans.service;

import com.example.loans.client.FraudDetectionClient;
import com.example.loans.dto.LoanDto;
import com.example.loans.model.Loan;
import com.example.loans.model.LoanStatus;
import com.example.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService {

    private final FraudDetectionClient fraudDetectionClient;
    private final LoanRepository loanRepository;
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
    public String applyLoan(LoanDto loanDto) {
        var loan = Loan.from(loanDto);
        log.info("Calling Fraud Detection Service for customer id: {}", loan.getCustomerId());
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
}
