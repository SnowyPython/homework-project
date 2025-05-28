package ru.vatolin.homeworkproject.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.vatolin.homeworkproject.entity.DataSourceErrorLog;
import ru.vatolin.homeworkproject.repository.ErrorLogRepository;

import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Component
@RequiredArgsConstructor
public class LogDataSourceErrorAspect {
    private final ErrorLogRepository errorLogRepository;

    @AfterThrowing(pointcut = "@annotation(ru.vatolin.homeworkproject.aop.annotation.LogDataSourceError)", throwing = "e")
    public void afterThrowingLogDataSourceErrorAdvice(JoinPoint joinPoint, Throwable e) {
        DataSourceErrorLog dataSourceErrorLog = new DataSourceErrorLog();

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String stackTraceString = sw.toString();

        dataSourceErrorLog.setStacktrace(stackTraceString);
        dataSourceErrorLog.setMessage(e.getMessage());
        dataSourceErrorLog.setSignature(joinPoint.getSignature().toShortString());

        errorLogRepository.save(dataSourceErrorLog);
    }
}
