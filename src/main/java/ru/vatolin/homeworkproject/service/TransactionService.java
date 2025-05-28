package ru.vatolin.homeworkproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vatolin.homeworkproject.aop.annotation.LogDataSourceError;
import ru.vatolin.homeworkproject.dto.TransactionDto;
import ru.vatolin.homeworkproject.entity.Transaction;
import ru.vatolin.homeworkproject.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @LogDataSourceError
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDto> transactionDtos = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDtos.add(new TransactionDto(transaction.getId(), transaction.getAccount().getId(),
                    transaction.getTransactionSum(), transaction.getTransactionTime()));
        }

        return transactionDtos;
    }
}
