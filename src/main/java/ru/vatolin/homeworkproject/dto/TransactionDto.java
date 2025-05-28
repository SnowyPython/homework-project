package ru.vatolin.homeworkproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private Long account_id;
    private BigDecimal transactionSum;
    private LocalDateTime transactionTime;
}
