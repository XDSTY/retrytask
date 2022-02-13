package com.xdsty.retrytask.policy;

import com.xdsty.retrytask.constant.BackOffTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BackOffPolicyFactory implements InitializingBean {

    private static final Map<Integer, BackOffPolicy> BACK_OFF_POLICY_MAP = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        BACK_OFF_POLICY_MAP.put(BackOffTypeEnum.EXPONENT_BACK_OFF.getCode(), new ExponentBackOffPolicy());
        BACK_OFF_POLICY_MAP.put(BackOffTypeEnum.FIX_BACK_OFF.getCode(), new FixBackOffPolicy());
    }

    public static BackOffPolicy getBackOffPolicy(Integer backOffType) {
        return BACK_OFF_POLICY_MAP.get(backOffType);
    }
}
