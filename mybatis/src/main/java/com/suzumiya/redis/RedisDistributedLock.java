package com.suzumiya.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisDistributedLock {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean acquireLock(String lockKey, String lockValue, long expireTimeInSeconds) {
        // 使用 Redis SETNX 命令尝试获取锁
        Boolean isLocked = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, expireTimeInSeconds, TimeUnit.SECONDS);
        return isLocked != null && isLocked;
    }

    public void releaseLock(String lockKey, String lockValue) {
        // 检查锁的当前持有者是否是当前线程，如果是，则释放锁
        String currentLockValue = redisTemplate.opsForValue().get(lockKey);
        if (lockValue.equals(currentLockValue)) {
            redisTemplate.delete(lockKey);
        }
    }
}
