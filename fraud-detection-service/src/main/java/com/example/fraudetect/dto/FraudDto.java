package com.example.fraudetect.dto;

import com.example.fraudetect.model.Fraud;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


public record FraudDto(String fraudRecordId, int customerId) {

    public static FraudDto from(Fraud fraud){
        return new FraudDto(fraud.getFraudRecordId(), fraud.getCustomerId());
    }
}
