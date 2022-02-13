package com.xdsty.retrytask.policy;

import com.xdsty.retrytask.constant.BackOffTypeEnum;
import com.xdsty.retrytask.entity.RetryBasicInfo;
import com.xdsty.retrytask.vo.RetryTaskContext;

public class FixBackOffPolicy implements BackOffPolicy {

    @Override
    public Integer getType() {
        return BackOffTypeEnum.FIX_BACK_OFF.getCode();
    }

    @Override
    public boolean canRetry(RetryTaskContext retryTaskContext) {
        RetryBasicInfo retryBasicInfo = retryTaskContext.getRetryTask().getRetryBasicInfo();
        return retryTaskContext.getRetryCount() < retryBasicInfo.getMaxRetryCount();
    }

    @Override
    public Integer getNextRetryInterval(RetryTaskContext retryTaskContext) {
        RetryBasicInfo retryBasicInfo = retryTaskContext.getRetryTask().getRetryBasicInfo();
        return retryBasicInfo.getBackOffTime();
    }
}
