package com.cignacmb.smart.base.common;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 约定：10 开头是系统问题
 * 20   开头是用户信息问题
 *
 * @Author: caokang
 * @Date: Created in 9:45 2020/4/30
 * @annotation:格式化返回客户端错误数据格式（json）
 * @version:2.0
 */
public class ReturnFormat {
    private static Map<String, String> messageMap = Maps.newHashMap();


    //初始化状态码与文字说明
    static {
        messageMap.put("0", "");

        messageMap.put("400", "Bad Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method Not Allowed");
        messageMap.put("406", "Not Acceptable");
        messageMap.put("500", "Internal Server Error");

        messageMap.put("1000", "[server]运行时异常");
        messageMap.put("1001", "[server]空值异常");
        messageMap.put("1002", "[server]数据类型转换异常");
        messageMap.put("1003", "[server]IO异常");
        messageMap.put("1004", "[server]未知方法异常");
        messageMap.put("1005", "[server]数组越界异常");
        messageMap.put("1006", "[server]网络异常");
        messageMap.put("1007", "[oracle]数据库异常");

        messageMap.put("2010", "用户未注册");
        messageMap.put("2011", "用户已注册");
        messageMap.put("2012", "用户名或密码错误");
        messageMap.put("2013", "用户帐号冻结");
        messageMap.put("2014", "用户信息编辑失败");
        messageMap.put("2015", "用户信息失效，请重新获取");

        //开发时可自定义 

    }

    /**
     * 定义response时通用的key及value
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
