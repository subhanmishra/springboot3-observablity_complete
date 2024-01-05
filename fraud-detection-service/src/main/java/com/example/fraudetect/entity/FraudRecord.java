package com.example.fraudetect.entity;

public record FraudRecord(Long id, String fraudRecordId, int customerId, LoanStatus loanStatus) {
}
