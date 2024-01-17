CREATE TABLE fraud_records
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    fraudRecordId VARCHAR(36) NOT NULL,
    customerId    INT         NOT NULL
);
