package com.xdsty.retrytask.strategy;

import com.xdsty.retrytask.util.RetryTaskException;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RetryTaskStrategyFactory {

    private static final ConcurrentHashMap<String, RetryTaskStrategy> RETRY_TASK_STRATEGY_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    public static void register(String key, RetryTaskStrategy strategy) {
        if (Objects.isNull(key) || Objects.isNull(strategy)) {
            throw new RetryTaskException("parameter can not null");
        }
        if (RETRY_TASK_STRATEGY_CONCURRENT_HASH_MAP.containsKey(key)) {
            throw new RetryTaskException(key + " have been existed");
        }
        RETRY_TASK_STRATEGY_CONCURRENT_HASH_MAP.put(key, strategy);
    }

    public static RetryTaskStrategy getRetryStrategy(String type) {
        return RETRY_TASK_STRATEGY_CONCURRENT_HASH_MAP.get(type);
    }

}
