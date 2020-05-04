package com.cignacmb.smart.base.utils.jobs;

/**
 * job相关的状态，与DB中的一致
 */
public class SmartJobStatus {
    //job
    public static final String JOB_EXEC_WAIT = "00";
    public static final String JOB_EXEC_RUN = "01";
    public static final String JOB_EXEC_ERR = "02";
    public static final String JOB_EXEC_STOP = "03";

    //task
    public static final String TASK_EXEC_RUN = "00";
    public static final String TASK_EXEC_STOP = "01";
    public static final String TASK_EXEC_ERR = "02";

    //group
    public static final String JOB_GROUP_CYCLE = "cycle";
    public static final String JOB_GROUP_ONCE = "once";
}
