package com.cignacmb.smart.base.utils.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 * @author r9wuxx
 */
public class RegexUtils {
    //年月
    public static final String YEARMONTH = "^\\d{4}(0[1-9]|11|12)$";
    //日期
    public static final String DATE = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    //身份证号
    public static final String IDNO = "^([1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])|([1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx])$";
    //TM虚拟身份证号 可为空或者15/18个0
    public static final String TM_IDNO = "([1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])|([1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx])|(000000000000000000)|(000000000000000)|^$";
    //年
    public static final String YEAR = "^\\d{4}$";
    //手机号码
    public static final String PHONE = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
    //固定电话
    public static final String FIXED_TELEPHONE = "^([+]{0,1}\\d{3,4}|\\d{3,4}-)?\\d{7,8}$";
    //邮箱
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*$";
    //整数
    public static final String INT = "^\\d{1,16}$";
    //最多一位小数
    public static final String DECIMAL_ONE  = "^\\d{1,10}(.\\d{1})?$";
    //最多两位小数
    public static final String DECIMAL_TWO = "^\\d{1,10}(.\\d{1,2})?$";
    //最多四位小数
    public static final String DECIMAL_FOUR = "^\\d{1,10}(.\\d{1,4})?$";
    //百分数-整数
    public static final String percentage_INT = "";
    //百分数-最多一位小数
    public static final String percentage_DECIMAL_ONE = "";
    //百分数-最多两位小数
    public static final String percentage_DECIMAL_TWO = "";
    //百分数-最多四位小数
    public static final String percentage_DECIMAL_FOUR = "";
    //百分数1到100的两位小数(包含0和100)
    public static final String percentage_0TO100 = "^\\d{1,2}(.\\d{1,2})?|100|100.0|100.00|0|0.0|0.00$";
    //百分数1到100的两位小数(包含100)
    public static final String percentage_TO100 = "^\\d{1,2}(.\\d{1,2})?|100|100.0|100.00$";
    //中文
    public static final String CHINESE = "^[\u0391-\uFFE5]+$";

    public static final String ENGLISH_AND_NUMBER = "^([0-9]|[A-z])+$";
    //纯数字
    public static final String NUMBER = "^[0-9]*$";

    /**
     * 正则校验
     * @param value
     * @param regex
     * @return
     */
    public static boolean isValid(String value, String regex) {

        if(regex == null || "".equals(regex) || value == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();

    }
}
