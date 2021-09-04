package com.lucas.homework;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisLockImp implements IRedisLock, AutoCloseable {
    private RedisClient client;
    private final String LOCK_LUA = "if(redis.call('setnx', KEYS[1], ARGV[2])==1) then" + "redis.call('pexpire', KEYS[1], ARGV[1]) return nil" + "else return redis.call('pttl', KEYS[1])";
    private final String UNLOCK_LUA = "if(redis.call('get', KEYS[1])==ARGV[1]) then" + "redis.call('del', KEYS[1]) return 1" + "else return nil";

    public RedisLockImp(String host, int port) {
        RedisURI redisUri = RedisURI.builder()
                .withHost(host)
                .withPort(port)
                .build();
        client = RedisClient.create(redisUri);
    }

    public RedisLockImp(RedisClient client) {
        this.client = client;
    }

    @Override
    public boolean tryLock(String key, long expire) {
        StatefulRedisConnection<String, String> connection = client.connect();
        try {
            String[] keys = new String[]{key};
            String value = "id" + Thread.currentThread().getId();
            String[] args = new String[]{String.valueOf(expire), value};
            RedisCommands<String, String> commands = connection.sync();
            Long result = commands.evalsha(LOCK_LUA, ScriptOutputType.INTEGER, keys, args);
            if (result == null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return false;
    }

    @Override
    public boolean tryUnlock(String key) {
        StatefulRedisConnection<String, String> connection = client.connect();
        try {
            String[] keys = new String[]{key};
            String[] args = new String[]{"id" + Thread.currentThread().getId()};
            RedisCommands<String, String> commands = connection.sync();
            Long result = commands.evalsha(UNLOCK_LUA, ScriptOutputType.INTEGER, keys, args);
            return result != null;
        } catch (Exception e) {

        } finally {
            connection.close();
        }
        return false;
    }

    @Override
    public void close() {
        if (client != null) {
            client.shutdown();
        }
    }
}
