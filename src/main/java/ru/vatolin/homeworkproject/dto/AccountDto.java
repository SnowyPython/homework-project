package ru.vatolin.homeworkproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.vatolin.homeworkproject.enums.AccountType;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private Long clientId;
    private AccountType accountType;
    private BigDecimal balance;
}
