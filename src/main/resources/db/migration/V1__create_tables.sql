CREATE TABLE clients (
    id BIGSERIAL primary key,
    lastname VARCHAR(50),
    firstname VARCHAR(50),
    middle_name VARCHAR(50),
    client_id UUID unique not null
);

CREATE TABLE accounts (
    id BIGSERIAL primary key,
    client_id BIGINT not null,
    account VARCHAR(10),
    balance NUMERIC(19, 2),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE transactions (
    id BIGSERIAL primary key,
    account_id BIGINT not null,
    transaction_sum NUMERIC(19, 2),
    transaction_time TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);

CREATE TABLE errors (
    id BIGSERIAL primary key,
    stacktrace TEXT,
    message VARCHAR(255),
    signature VARCHAR(255)
);