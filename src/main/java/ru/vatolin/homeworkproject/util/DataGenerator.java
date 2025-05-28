package ru.vatolin.homeworkproject.util;

import org.springframework.stereotype.Component;
import ru.vatolin.homeworkproject.entity.Account;
import ru.vatolin.homeworkproject.entity.Client;
import ru.vatolin.homeworkproject.entity.Transaction;
import ru.vatolin.homeworkproject.enums.AccountType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class DataGenerator {
    private static final String[] FIRST_NAMES = {"Александр", "Михаил", "Иван", "Дмитрий", "Сергей", "Андрей", "Алексей"};
    private static final String[] LAST_NAMES = {"Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов", "Попов", "Васильев"};
    private static final String[] MIDDLE_NAMES = {"Иванович", "Петрович", "Михаилович", "Александрович", "Алексеевич", "Андреевич"};

    private static final int SCALE = 2;

    private static final Random random = new Random();

    public List<Client> generateClients() {
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String[] names = generateNames();

            Client client = new Client();
            client.setLastname(names[0]);
            client.setFirstname(names[1]);
            client.setMiddleName(names[2]);
            client.setClientId(UUID.randomUUID());

            clients.add(client);
        }

        return clients;
    }

    public List<Account> generateAccounts(List<Client> clients) {
        List<Account> accounts = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Client client = clients.get(random.nextInt(clients.size()));

            Account account = new Account();
            account.setClient(client);
            account.setAccount(getRandomType());
            account.setBalance(generateRandomBigDecimal());

            accounts.add(account);
        }

        return accounts;
    }

    public List<Transaction> generateTransactions(List<Account> accounts) {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Account account = accounts.get(random.nextInt(accounts.size()));

            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setTransactionSum(generateRandomBigDecimal());
            transaction.setTransactionTime(generateRandomLocalDateTime());

            transactions.add(transaction);
        }

        return transactions;
    }

    private String[] generateNames() {
        String firstname = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastname = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String middleName = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];

        return new String[]{lastname, firstname, middleName};
    }

    private AccountType getRandomType() {
        AccountType[] types = AccountType.values();
        return types[random.nextInt(types.length)];
    }

    private BigDecimal generateRandomBigDecimal() {
        BigDecimal maxValue = new BigDecimal("99999999999999999.99");

        return BigDecimal.valueOf(random.nextDouble())
                .multiply(maxValue)
                .setScale(SCALE, RoundingMode.HALF_UP);
    }

    private LocalDateTime generateRandomLocalDateTime() {
        long minDay = LocalDateTime.of(1970, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long maxDay = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long randomDay = minDay + (long) (random.nextDouble() * (maxDay - minDay));

        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC);
    }
}
