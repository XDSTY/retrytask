package com.xdsty.retrytask.strategy;

import com.xdsty.retrytask.util.RetryTaskException;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RetryTaskStrategyFactory {

    private static final ConcurrentHashMap<String, RetryTaskStrategy> retryTaskStrategyMap = new ConcurrentHashMap<>();

    public static void register(String key, RetryTaskStrategy strategy) {
        if (Objects.isNull(key) || Objects.isNull(strategy)) {
            throw new RetryTaskException("parameter can not null");
        }
        if (retryTaskStrategyMap.containsKey(key)) {
            throw new RetryTaskException(key + " have been existed");
        }
        retryTaskStrategyMap.put(key, strategy);
    }

}
