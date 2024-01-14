package com.example.fraudetect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("fraud_records")
public record FraudRecord(@Id Long id, @Column("fraudRecordId") String fraudRecordId, @Column("customerId") int customerId) {
}
