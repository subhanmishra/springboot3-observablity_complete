package com.example.fraudetect.model;

import com.example.fraudetect.dto.FraudDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("fraud_records")
public class Fraud {

    @Id
    private Long id;
    @Column("fraudRecordId")
    private String fraudRecordId;
    @Column("customerId")
    private int customerId;

    public static Fraud from(FraudDto fraudDto) {

        return Fraud.builder()
                .fraudRecordId(fraudDto.fraudRecordId())
                .customerId(fraudDto.customerId())
                .build();

    }
}
