package com.xdsty.retrytask.constant;

public class RetrySQLConstant {

    /**
     * 插入任务sql
     */
    public static final String INSERT_TASK = "insert into retry_task (task_type, retry_basic_info_json, back_off_type, retry_type, task_status, retry_count, create_user) " +
            "value(?, ?, ?, ?, ?, ?, ?);";

    /**
     * 更新任务为开始
     */
    public static final String TASK_START = "update retry_task set start_execute_time = ?, task_status = ? where id = ?;";

    /**
     * 更新任务为失败/成功
     */
    public static final String TASK_FAIL = "update retry_task set finish_time = ?, task_status = ? where id = ?;";

}
