package com.cignacmb.smart.base.utils.excel;

import com.cignacmb.smart.base.annotation.FieldDefaultValue;
import com.cignacmb.smart.base.annotation.FieldEnum;
import com.cignacmb.smart.base.annotation.FieldReg;
import com.cignacmb.smart.base.annotation.FieldRely;
import com.cignacmb.smart.base.annotation.excel.ColumnMapFields;
import com.cignacmb.smart.base.annotation.excel.ColumnType;
import com.cignacmb.smart.base.annotation.excel.Excel;
import com.cignacmb.smart.base.annotation.excel.ExcelColumn;
import com.cignacmb.smart.base.validation.groups.First;
import com.cignacmb.smart.base.validation.groups.Second;
import com.cignacmb.smart.entity.base.BaseExcelImportEntity;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@GroupSequence({First.class, Second.class, ExcelDemo.class})
@FieldRely(relyField="agentcode", currentField="agentnum", message="员工代码为空时，员工编号必填",
        relyType=FieldRely.RelyType.notnull2null, groups= {Second.class})
@FieldRely(relyField="agentstate", currentField="employdate", message="员工状态为在职时，入职日期必填",
        relyValues={"01"},relyType=FieldRely.RelyType.value2notnull, groups= {Second.class})
@Excel(mapping = Excel.Mapping.title, excelId = "TestExcelDemo")
@ColumnMapFields(mappings = {"agentcode", "agentnum", "agentstate", "branchtype", "managecom", "employdate",
        "testnum"/*, "testnum1" */})
public class ExcelDemo extends BaseExcelImportEntity implements Serializable {
    @ExcelColumn(index = 0, title = "员工代码")
    @NotEmpty(groups = First.class, message = "员工代码不能为空")
    private String agentcode;

    @ExcelColumn(index = 4, title = "管理机构")
    @Size(min = 5, max = 5, message="管理机构长度必须为5", groups = Second.class)
    @NotEmpty(groups = First.class, message = "管理机构不能为空")
    private String managecom;

    @ExcelColumn(index = 3, title = "渠道")
    @FieldReg(regexp="^\\d+$", message="渠道必须为纯数字", groups = First.class)
//	@Pattern(regexp="^\\d+$", message="渠道必须为纯数字", groups = First.class)
    private String branchtype;

    @ExcelColumn(index = 1, title = "员工编号")
    private String agentnum;

    @ExcelColumn(index = 2, title = "员工状态", groups={First.class})
    @FieldEnum(value = {"01","02","03"}, message="员工状态", groups={Second.class})
    private String agentstate;

    @ExcelColumn(index = 5, title = "入职日期", type = ColumnType.Type.date, groups={First.class})
    @PastOrPresent(message="日期要小于等于当天")
    private Date employdate;

    @ExcelColumn(index = 6, title = "数值校验", type = ColumnType.Type.number, groups={First.class})
    @Digits(fraction = 2, integer = 5, groups= {Second.class}, message="小数点不对")
    @DecimalMax(value = "500", message = "最大也是500")
    @FieldDefaultValue("499")
    private BigDecimal testnum;

    @ExcelColumn(index = 7, title = "下拉校验", type = ColumnType.Type.enums, groups={First.class})
    private String testnum1;

    @FieldDefaultValue("test")
    private String testDefault;

    public String getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    public String getManagecom() {
        return managecom;
    }

    public void setManagecom(String managecom) {
        this.managecom = managecom;
    }

    public String getBranchtype() {
        return branchtype;
    }

    public void setBranchtype(String branchtype) {
        this.branchtype = branchtype;
    }

    public String getAgentnum() {
        return agentnum;
    }

    public void setAgentnum(String agentnum) {
        this.agentnum = agentnum;
    }

    public String getAgentstate() {
        return agentstate;
    }

    public void setAgentstate(String agentstate) {
        this.agentstate = agentstate;
    }

    public Date getEmploydate() {
        return employdate;
    }

    public void setEmploydate(Date employdate) {
        this.employdate = employdate;
    }

    public BigDecimal getTestnum() {
        return testnum;
    }

    public void setTestnum(BigDecimal testnum) {
        this.testnum = testnum;
    }

    public String getTestnum1() {
        return testnum1;
    }

    public void setTestnum1(String testnum1) {
        this.testnum1 = testnum1;
    }

    public String getTestDefault() {
        return testDefault;
    }

    public void setTestDefault(String testDefault) {
        this.testDefault = testDefault;
    }
}
