package com.cignacmb.smart.base.utils.excel;

public enum ExcelConst {

    /**
     * 如果不符合SMART约定的日期格式，则使用该值标记日期格式错误
     */
    VALID_DATE_FALSE("9999-09-09"),

    /**
     * 如果excel中的内容不能转换成数字，则使用该值替代，便于后续的validation可以正常进行，而不是在set JavaBean的时候报转换异常
     */
    VALID_NUMBER_FALSE("1234567890"),

    /**
     * EXCEL导出类型
     */
    EXPORT_TYPE_COMMON("common"),
    EXPORT_TYPE_COMMON_PAGE("common_page"),
    EXPORT_TYPE_ASYN("asyn"),
    EXPORT_DATALIST_KEY("dataList"),

    /**
     * EXCEL上传时，如果需要导入中间表，需要提供中间表的batchsn，该sn会放入Map中，默认提供的key为“batchsn”
     */
    IMPORT_BATCHSN_KEY("batchsn"),
    /**
     * 校验结果所在的sheet名称
     */
    IMPORT_ERROR_SHEET("errorMsg"),

    /**
     * el表达式
     */
    EL_LENGTH("le:"),
    EL_FOREACH("fe:"),
    EL_FOREACH_NOT_CREATE("!fe:"),
    EL_FOREACH_AND_SHIFT("$fe:"),
    EL_FOREACH_COL("#fe:"),
    EL_FOREACH_COL_VALUE("v_fe:"),
    EL_START_STR("{{"),
    EL_END_STR("}}"),
    EL_WRAP("]]"),
    EL_NUMBER_SYMBOL("n:"),
    EL_FORMAT_DATE("fd:"),
    EL_FORMAT_NUMBER("fn:"),
    EL_SUM("sum:"),
    EL_IF_DELETE("!if:"),
    EL_EMPTY(""),
    EL_CONST("'"),
    EL_NULL("&NULL&"),
    EL_LEFT_BRACKET("("),
    EL_RIGHT_BRACKET(")");

    private String value;

    private ExcelConst(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
