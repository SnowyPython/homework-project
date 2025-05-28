package ru.vatolin.homeworkproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vatolin.homeworkproject.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
