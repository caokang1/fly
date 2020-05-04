package com.cignacmb.smart.base.utils.excel;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

public class TemplateImportResult<T> {

    /**
     * 结果集
     */
    private List<T> list = new ArrayList<>();

    /**
     * 错误信息
     */
    private List<String> errorList = new ArrayList<String>();

    /**
     * 导入结果
     */
    private boolean importFlag = false;

    /**
     * 导入信息
     */
    private String importMsg;

    /**
     * 校验结果
     */
    private boolean verify = false;

    /**
     * 上传的Excel以及对应的错误信息
     */
    private Workbook wb;

    /**
     * Excel模板id
     */
    private String excelId;

    /**
     * 上传类型
     */
    private boolean async;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public boolean isImportFlag() {
        return importFlag;
    }

    public void setImportFlag(boolean importFlag) {
        this.importFlag = importFlag;
    }

    public String getImportMsg() {
        return importMsg;
    }

    public void setImportMsg(String importMsg) {
        this.importMsg = importMsg;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public Workbook getWb() {
        return wb;
    }

    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    public String getExcelId() {
        return excelId;
    }

    public void setExcelId(String excelId) {
        this.excelId = excelId;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }
}
