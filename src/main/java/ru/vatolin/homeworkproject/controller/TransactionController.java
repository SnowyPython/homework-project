package ru.vatolin.homeworkproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vatolin.homeworkproject.dto.TransactionDto;
import ru.vatolin.homeworkproject.service.TransactionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();

        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "data", transactions
                )
        );
    }
}
