package com.lucas.homework;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisIncreaseApi {
    private RedisClient client;

    public RedisIncreaseApi(String host, int port) {
        RedisURI redisUri = RedisURI.builder()
                .withHost(host)
                .withPort(port)
                .build();
        client = RedisClient.create(redisUri);
    }

    private boolean decrease() throws Exception {
        String key = "key1";
        IRedisLock lock = new RedisLockImp(client);
        try {
            lock.tryLock(key, 5000);
            StatefulRedisConnection<String, String> connection = client.connect();
            RedisCommands<String, String> commands = connection.sync();
            Long result = commands.decr(key);
            if (result >= 0) {
                return true;
            }
            throw new Exception("Out of stock");
        } finally {
            lock.tryUnlock(key);
        }
    }
}
