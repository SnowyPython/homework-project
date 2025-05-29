package ru.vatolin.homeworkproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vatolin.homeworkproject.entity.TimeLimitExceedLog;

public interface TimeLimitExceedLogRepository extends JpaRepository<TimeLimitExceedLog, Long> {
}
