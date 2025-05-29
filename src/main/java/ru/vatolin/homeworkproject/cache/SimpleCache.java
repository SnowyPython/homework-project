package ru.vatolin.homeworkproject.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleCache {
    private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();

    public Object get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key);
            return null;
        }
        return entry.getValue();
    }

    public void put(String key, Object value, long ttlMillis) {
        cache.put(key, new CacheEntry(value, ttlMillis));
    }

    public void clear() {
        cache.clear();
    }
}
