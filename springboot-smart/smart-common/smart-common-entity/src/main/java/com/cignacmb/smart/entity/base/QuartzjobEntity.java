package com.cignacmb.smart.entity.base;

/**
 * Quartz实体
 * @author r9wuxx
 */
public class QuartzjobEntity extends BaseEntity<QuartzjobEntity> {
    /**
     * 主键
     */
    private String jobid;

    /**
     * 名称
     */
    private String jobname;

    /**
     * IP
     */
    private String ip;

    /**
     * 对应的类（带包名）
     */
    private String classname;

    /**
     * CRON表达式
     */
    private String cronexpression;

    /**
     * 状态，00-停用，01-启用
     */
    private String jobstatus;

    /**
     * 执行状态，00-等待，01-执行中，02-最近一次执行异常  03-执行完成
     */
    private String execstatus;

    /**
     * 参数，JSON格式
     */
    private String jobparams;

    /**
     * 批处理组，cycle-循环，once-一次
     */
    private String jobgroup;

    /**
     * 是否在schedule中
     */
    private boolean isScheduled;

    /**
     * 描述
     */
    private String jobdesc;

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getCronexpression() {
        return cronexpression;
    }

    public void setCronexpression(String cronexpression) {
        this.cronexpression = cronexpression;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getExecstatus() {
        return execstatus;
    }

    public void setExecstatus(String execstatus) {
        this.execstatus = execstatus;
    }

    public String getJobparams() {
        return jobparams;
    }

    public void setJobparams(String jobparams) {
        this.jobparams = jobparams;
    }

    public String getJobgroup() {
        return jobgroup;
    }

    public void setJobgroup(String jobgroup) {
        this.jobgroup = jobgroup;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }
}
