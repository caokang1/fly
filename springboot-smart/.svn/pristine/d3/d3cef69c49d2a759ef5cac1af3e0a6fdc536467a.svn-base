package com.cignacmb.smart.entity.base;

import java.io.Serializable;
import java.util.Map;

public class BaseExcelImportEntity implements Serializable {
    /**
     * 行号
     */
    private int rowno;

    /**
     * 错误信息
     */
    private Map<Integer, String> errorMap;

    /**
     * 是否通过校验，默认TRUE
     */
    private boolean isvalid = true;

    /**
     * excel中的列号对应的java field
     */
    private Map<Integer, String> columnFieldMap;

    public int getRowno() {
        return rowno;
    }

    public void setRowno(int rowno) {
        this.rowno = rowno;
    }

    public Map<Integer, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<Integer, String> errorMap) {
        this.errorMap = errorMap;
    }

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public Map<Integer, String> getColumnFieldMap() {
        return columnFieldMap;
    }

    public void setColumnFieldMap(Map<Integer, String> columnFieldMap) {
        this.columnFieldMap = columnFieldMap;
    }
}
