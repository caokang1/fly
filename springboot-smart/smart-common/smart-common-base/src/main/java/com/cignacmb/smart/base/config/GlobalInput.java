/**
 * Copyright (c) 2002 sinosoft  Co. Ltd.
 * All right reserved.
 */
package com.cignacmb.smart.base.config;

/**
 * <p>Title: Webҵ��ϵͳ</p>
 * <p>Description: ȫ�ֱ�����</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sinosoft</p>
 * @author YT
 * @version 1.0
 * <p>���Ӽ�ҵ�������Ϊ�����ҵƽ̨ 2006-10-25 ����</p>
 */

public class GlobalInput
{
    /** ��ǰ���� */
    public String BranchType;
    /** ��ǰ����Ա */
    public String Operator;
    /** ��ǰ������� */
    public String ManageCom;
    /** ��ǰ��½���� */
    public String ComCode;
    /** ��ҵ������� */
    public String AgentCom;
    /** ���л��� ��������м��á�,������*/
    public String ComCodes;

//  /** ��ǰ���� */
//  public String RiskCode;
//  /** ��ǰ���ְ汾 */
//  public String RiskVersion;

    public GlobalInput()
    {
    }

    /**
     * ����GlobalInput����֮���ֱ�Ӹ���
     * @param cGlobalInput �����о���ֵ��GlobalInput����
     */
    public void setSchema(GlobalInput cGlobalInput)
    {
        //��ȡ��½�û�������Ϣ���û����롢���������
        this.BranchType = cGlobalInput.BranchType;
        this.Operator = cGlobalInput.Operator;
        this.ComCode = cGlobalInput.ComCode;
        this.ManageCom = cGlobalInput.ManageCom;
        this.AgentCom = cGlobalInput.AgentCom;
        this.ComCodes = cGlobalInput.ComCodes;
        
    }
}
