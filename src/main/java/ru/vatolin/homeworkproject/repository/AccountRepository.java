package ru.vatolin.homeworkproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vatolin.homeworkproject.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
