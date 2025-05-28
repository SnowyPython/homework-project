package ru.vatolin.homeworkproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vatolin.homeworkproject.dto.AccountDto;
import ru.vatolin.homeworkproject.service.AccountService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();

        return ResponseEntity.ok(
                Map.of(
                        "status", "success",
                        "data", accounts
                )
        );
    }
}
