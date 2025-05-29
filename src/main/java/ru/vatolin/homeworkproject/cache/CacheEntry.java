package ru.vatolin.homeworkproject.cache;

import lombok.Getter;

public class CacheEntry {
    @Getter
    private final Object value;
    private final long expireAt;

    public CacheEntry(Object value, long ttlMillis) {
        this.value = value;
        this.expireAt = System.currentTimeMillis() + ttlMillis;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireAt;
    }
}

