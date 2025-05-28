package ru.vatolin.homeworkproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vatolin.homeworkproject.entity.DataSourceErrorLog;

public interface ErrorLogRepository extends JpaRepository<DataSourceErrorLog, Long> {
}
