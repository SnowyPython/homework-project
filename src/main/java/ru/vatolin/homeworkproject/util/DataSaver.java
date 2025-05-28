package ru.vatolin.homeworkproject.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vatolin.homeworkproject.entity.Account;
import ru.vatolin.homeworkproject.entity.Client;
import ru.vatolin.homeworkproject.repository.AccountRepository;
import ru.vatolin.homeworkproject.repository.ClientRepository;
import ru.vatolin.homeworkproject.repository.TransactionRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSaver {
    private final DataGenerator dataGenerator;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public void clearAndGenerate() {
        transactionRepository.deleteAllInBatch();
        accountRepository.deleteAllInBatch();
        clientRepository.deleteAllInBatch();

        saveGeneratedData();
    }

    private void saveGeneratedData() {
        List<Client> savedClients = clientRepository.saveAll(dataGenerator.generateClients());
        List<Account> savedAccounts = accountRepository.saveAll(dataGenerator.generateAccounts(savedClients));
        transactionRepository.saveAll(dataGenerator.generateTransactions(savedAccounts));
    }
}
