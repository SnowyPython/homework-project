package ru.vatolin.homeworkproject.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.vatolin.homeworkproject.cache.SimpleCache;
import ru.vatolin.homeworkproject.config.AppCacheProperties;

@Aspect
@Component
@RequiredArgsConstructor
public class CacheAspect {

    private final SimpleCache cache;
    private final AppCacheProperties appCacheProperties;

    @Around("@annotation(ru.vatolin.homeworkproject.aop.annotation.Cached)")
    public Object cacheAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = generateKey(joinPoint);

        Object cachedValue = cache.get(key);
        if (cachedValue != null) {
            return cachedValue;
        }

        Object result = joinPoint.proceed();

        cache.put(key, result, appCacheProperties.getTtlMs());
        return result;
    }

    private String generateKey(ProceedingJoinPoint joinPoint) {
        StringBuilder keyBuilder = new StringBuilder(joinPoint.getSignature().toShortString());
        for (Object arg : joinPoint.getArgs()) {
            keyBuilder.append(":").append(arg != null ? arg.hashCode() : "null");
        }
        return keyBuilder.toString();
    }
}
