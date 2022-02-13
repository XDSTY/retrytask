package com.xdsty.retrytask.strategy;

import org.springframework.stereotype.Component;

@Component
public class TestStrategy implements RetryTaskStrategy {

    @Override
    public void execute(String requestJson) {
        System.out.println("TestStrategy execute...");
    }

    @Override
    public String getType() {
        return "test";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RetryTaskStrategyFactory.register("test", this);
    }
}
