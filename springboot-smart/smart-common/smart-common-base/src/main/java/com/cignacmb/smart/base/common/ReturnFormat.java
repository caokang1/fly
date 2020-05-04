package com.cignacmb.smart.base.common;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Լ����10 ��ͷ��ϵͳ����
 * 20   ��ͷ���û���Ϣ����
 *
 * @Author: caokang
 * @Date: Created in 9:45 2020/4/30
 * @annotation:��ʽ�����ؿͻ��˴������ݸ�ʽ��json��
 * @version:2.0
 */
public class ReturnFormat {
    private static Map<String, String> messageMap = Maps.newHashMap();


    //��ʼ��״̬��������˵��
    static {
        messageMap.put("0", "");

        messageMap.put("400", "Bad Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method Not Allowed");
        messageMap.put("406", "Not Acceptable");
        messageMap.put("500", "Internal Server Error");

        messageMap.put("1000", "[server]����ʱ�쳣");
        messageMap.put("1001", "[server]��ֵ�쳣");
        messageMap.put("1002", "[server]��������ת���쳣");
        messageMap.put("1003", "[server]IO�쳣");
        messageMap.put("1004", "[server]δ֪�����쳣");
        messageMap.put("1005", "[server]����Խ���쳣");
        messageMap.put("1006", "[server]�����쳣");
        messageMap.put("1007", "[oracle]���ݿ��쳣");

        messageMap.put("2010", "�û�δע��");
        messageMap.put("2011", "�û���ע��");
        messageMap.put("2012", "�û������������");
        messageMap.put("2013", "�û��ʺŶ���");
        messageMap.put("2014", "�û���Ϣ�༭ʧ��");
        messageMap.put("2015", "�û���ϢʧЧ�������»�ȡ");

        //����ʱ���Զ��� 

    }

    /**
     * ����responseʱͨ�õ�key��value
     */
    public static final String RESPONSE_CODE = "code";
    public static final String RESPONSE_MESSAGE_CODE = "msg";
    public static final String RESPONSE_DATA_CODE = "data";

    public static final String RESPONSE_SUCCESS_CODE_0 = "0";
    public static final String RESPONSE_FAIL_CODE_9999 = "9999";
    public static final String RESPONSE_SUCCESS_IMPORT_CODE = "1";


    public static ResponseData retParam(String status, Object data) {
        ResponseData responseData = new ResponseData();
        if (status.equals(ReturnFormat.RESPONSE_SUCCESS_CODE_0)) {
            return responseData.success();
        } else {
            responseData.fail((String.valueOf(status) == null ? "" : String.valueOf(status))
                    + " : " + (messageMap.get(String.valueOf(status)) == null ? " " : messageMap.get(String.valueOf(status)) + "-")
                    + JSON.toJSONString(data));
            return responseData;
        }
    }

}
