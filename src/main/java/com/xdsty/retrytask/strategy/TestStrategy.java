package com.xdsty.retrytask.strategy;

public class TestStrategy implements RetryTaskStrategy {

    @Override
    public void execute(String requestJson) {

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
