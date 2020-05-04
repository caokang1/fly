package com.cignacmb.smart.base.common;

/**
 * @author r9wuxx
 */

public enum Constant {

    /**
     * token的key
     */
    TOKEN_KEY("token"),

    /**
     * 系统批处理默认执行用户名称
     */
    BATCH_OPERATOR("batch"),

    /**
     * quartz写入JobDataMap时默认使用的key
     */
    QUARTZ_JOB_ENTITY("entity");

    private String value;

    private Constant(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}