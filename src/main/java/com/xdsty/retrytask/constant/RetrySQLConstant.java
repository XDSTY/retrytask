package com.xdsty.retrytask.constant;

public class RetrySQLConstant {

    public static final String INSERT_TASK = "insert into retry_task (task_type, retry_basic_info_json, back_off_type, retry_type, task_status, retry_count) " +
            "value(?, ?, ?, ?, ?, ?);";

}
