package com.example.loans.dto;

import com.example.loans.model.Loan;
import com.example.loans.model.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record LoanDto(String loanId, String customerName, int customerId, BigDecimal amount, LoanStatus loanStatus) {

    public static LoanDto from(Loan loan) {
        return new LoanDto(loan.getLoanId(), loan.getCustomerName(), loan.getCustomerId(),
                loan.getAmount(), loan.getLoanStatus());
    }
}
