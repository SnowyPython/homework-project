package ru.vatolin.homeworkproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vatolin.homeworkproject.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
