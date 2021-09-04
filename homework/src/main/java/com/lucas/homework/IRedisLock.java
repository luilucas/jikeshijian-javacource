package com.lucas.homework;

public interface IRedisLock {

    boolean tryLock(String key, long expire);

    boolean tryUnlock(String key);
}
