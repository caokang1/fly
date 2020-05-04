/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */
package com.cignacmb.smart.base.config;

/**
 * <p>Title: Web业务系统</p>
 * <p>Description: 全局变量区</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sinosoft</p>
 * @author YT
 * @version 1.0
 * <p>增加兼业代理机构为满足兼业平台 2006-10-25 周磊</p>
 */

public class GlobalInput
{
    /** 当前渠道 */
    public String BranchType;
    /** 当前操作员 */
    public String Operator;
    /** 当前管理机构 */
    public String ManageCom;
    /** 当前登陆机构 */
    public String ComCode;
    /** 兼业代理机构 */
    public String AgentCom;
    /** 所有机构 多个机构中间用“,”隔开*/
    public String ComCodes;

//  /** 当前险种 */
//  public String RiskCode;
//  /** 当前险种版本 */
//  public String RiskVersion;

    public GlobalInput()
    {
    }

    /**
     * 两个GlobalInput对象之间的直接复制
     * @param cGlobalInput 包含有具体值的GlobalInput对象
     */
    public void setSchema(GlobalInput cGlobalInput)
    {
        //获取登陆用户基础信息：用户编码、管理机构等
        this.BranchType = cGlobalInput.BranchType;
        this.Operator = cGlobalInput.Operator;
        this.ComCode = cGlobalInput.ComCode;
        this.ManageCom = cGlobalInput.ManageCom;
        this.AgentCom = cGlobalInput.AgentCom;
        this.ComCodes = cGlobalInput.ComCodes;
        
    }
}
