package ru.vatolin.homeworkproject.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.vatolin.homeworkproject.config.AppLimitsProperties;
import ru.vatolin.homeworkproject.entity.TimeLimitExceedLog;
import ru.vatolin.homeworkproject.repository.TimeLimitExceedLogRepository;

@Aspect
@Component
@RequiredArgsConstructor
public class MetricAspect {
    private final AppLimitsProperties appLimitsProperties;
    private final TimeLimitExceedLogRepository timeLimitExceedLogRepository;

    @Around("@annotation(ru.vatolin.homeworkproject.aop.annotation.Metric)")
    public Object execTimeAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long beforeTime = System.currentTimeMillis();
        long timeout = appLimitsProperties.getMethodTimeoutMs();
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } finally {
            long afterTime = System.currentTimeMillis();
            long totalTime = afterTime - beforeTime;
            if (totalTime  > timeout) {
                TimeLimitExceedLog timeLimitExceedLog = new TimeLimitExceedLog();
                timeLimitExceedLog.setSignature(proceedingJoinPoint.getSignature().toShortString());
                timeLimitExceedLog.setTime(totalTime);

                timeLimitExceedLogRepository.save(timeLimitExceedLog);
            }
        }

        return result;
    }
}
