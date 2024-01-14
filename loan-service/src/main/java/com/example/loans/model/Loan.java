package com.example.loans.model;

import com.example.loans.dto.LoanDto;
import com.fasterxml.jackson.databind.EnumNamingStrategies;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("loans")
public class Loan {
    @Id
    private Long id;
    @Column("loanId")
    private String loanId;
    @Column("customerName")
    private String customerName;
    @Column("customerId")
    private int customerId;
    @Column("amount")
    private BigDecimal amount;
    @Column("loanStatus")
    private LoanStatus loanStatus;

    public static Loan from(LoanDto loanDto) {

        return Loan.builder()
                .loanId(loanDto.loanId())
                .amount(loanDto.amount())
                .customerId(loanDto.customerId())
                .customerName(loanDto.customerName())
                .build();
    }
}
