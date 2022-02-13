package com.xdsty.retrytask.policy;

import com.xdsty.retrytask.constant.BackOffTypeEnum;
import com.xdsty.retrytask.entity.RetryBasicInfo;
import com.xdsty.retrytask.vo.RetryTaskContext;

public class ExponentBackOffPolicy implements BackOffPolicy {

    @Override
    public Integer getType() {
        return BackOffTypeEnum.EXPONENT_BACK_OFF.getCode();
    }

    @Override
    public boolean canRetry(RetryTaskContext retryTaskContext) {
        RetryBasicInfo retryBasicInfo = retryTaskContext.getRetryTask().getRetryBasicInfo();
        return retryTaskContext.getRetryCount() < retryBasicInfo.getMaxRetryCount();
    }

    @Override
    public Integer getNextRetryInterval(RetryTaskContext retryTaskContext) {
        RetryBasicInfo retryBasicInfo = retryTaskContext.getRetryTask().getRetryBasicInfo();
        // 指数递增
        Integer intervalTime = (int)(retryBasicInfo.getBackOffTime() * retryBasicInfo.getExponentValue());
        intervalTime = intervalTime > retryBasicInfo.getExponentMaxBackOffTime() ? retryBasicInfo.getExponentMaxBackOffTime() : intervalTime;
        retryBasicInfo.setBackOffTime(intervalTime);
        return intervalTime;
    }
}
