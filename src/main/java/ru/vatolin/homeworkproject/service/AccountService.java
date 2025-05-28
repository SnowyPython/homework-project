package ru.vatolin.homeworkproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vatolin.homeworkproject.aop.annotation.LogDataSourceError;
import ru.vatolin.homeworkproject.dto.AccountDto;
import ru.vatolin.homeworkproject.entity.Account;
import ru.vatolin.homeworkproject.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @LogDataSourceError
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();

        for (Account account : accounts) {
            accountDtos.add(new AccountDto(account.getId(), account.getClient().getId(),
                    account.getAccount(), account.getBalance()));
        }

        return accountDtos;
    }
}
