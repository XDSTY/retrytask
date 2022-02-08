package com.xdsty.retrytask.servcie;

import com.xdsty.retrytask.util.ConnectionUtil;
import com.xdsty.retrytask.vo.ExponentBackOffRetryTask;
import com.xdsty.retrytask.vo.FixBackOffRetryTask;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class RetryTaskServiceImpl implements RetryTaskService {

    private ThreadPoolExecutor poolExecutor;

    private ConnectionUtil connectionUtil;

    public RetryTaskServiceImpl(ThreadPoolExecutor poolExecutor, ConnectionUtil connectionUtil) {
        this.poolExecutor = poolExecutor;
        this.connectionUtil = connectionUtil;
    }

    @Override
    public void submitFixBackOffTask(FixBackOffRetryTask task) {

    }

    @Override
    public void submitExponentTask(ExponentBackOffRetryTask task) {

    }
}
