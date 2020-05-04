package com.cignacmb.smart.base.utils.excel;

import com.cignacmb.smart.base.common.UserInfo;
import com.cignacmb.smart.base.utils.file.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateExportInfo {

    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 导出类型
     */
    private String exportType;
    /**
     * 是否多个sheet模板，默认false
     */
    private boolean multiSheet;
    /**
     * sheet个数，仅在multiSheet==TRUE时才会使用
     */
    private int sheetNum;
    /**
     * EXCEL ID，作为数据库表中的文件下载类型，默认使用模板名称
     */
    private String excelId;
    /**
     * 生成的Excel文件存放的上级目录，默认取ExcelId
     */
    private String preDir;
    /**
     * Excel文件类型
     */
    private String fileType;
    /**
     * 用户信息
     */
    private UserInfo userInfo;
    /**
     * 参数集合，与sheetIndex一致
     */
    private List<Map<String, Object>> paramsList;

    /**
     * 模板中el表达式的key
     */
    private String dataListKey;

    /**
     * 数据查询接口
     */
    private ExcelExportListService excelExportListService;

    public static final class Builder {
        private String templateName;
        private String exportType;
        private boolean multiSheet = false;
        private int sheetNum = 1;
        private String excelId;
        private String preDir;
        private String fileType;
        private UserInfo userInfo;
        private List<Map<String, Object>> paramsList = new ArrayList<>();
        private String dataListKey = ExcelConst.EXPORT_DATALIST_KEY.getValue();
        private ExcelExportListService excelExportListService;

        private Builder() {
        }

        public static Builder aTemplateExportInfo() {
            return new Builder();
        }

        public Builder withTemplateName(String templateName) {
            this.templateName = templateName;
            this.excelId = FileUtils.splitFileName(templateName)[0];
            this.fileType = FileUtils.splitFileName(templateName)[1];
            this.preDir = this.excelId;
            return this;
        }

        public Builder withExportType(String exportType) {
            this.exportType = exportType;
            return this;
        }

        public Builder withMultiSheet(boolean multiSheet) {
            this.multiSheet = multiSheet;
            return this;
        }

        public Builder withSheetNum(int sheetNum) {
            this.sheetNum = sheetNum;
            return this;
        }

        public Builder withUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
            return this;
        }

        public Builder withParamsList(Map<String, Object> ... params){
            for (Map<String, Object> param : params){
                this.paramsList.add(param);
            }
            return this;
        }

        public Builder withParamsList(List<Map<String, Object>> paramsList) {
            this.paramsList = paramsList;
            return this;
        }

        public Builder withDataListKey(String dataListKey){
            this.dataListKey = dataListKey;
            return this;
        }

        public Builder withExcelExportService(ExcelExportListService excelExportListService) {
            this.excelExportListService = excelExportListService;
            return this;
        }

        public TemplateExportInfo build() {
            TemplateExportInfo templateExportInfo = new TemplateExportInfo();
            templateExportInfo.excelId = this.excelId;
            templateExportInfo.excelExportListService = this.excelExportListService;
            templateExportInfo.templateName = this.templateName;
            templateExportInfo.exportType = this.exportType;
            templateExportInfo.fileType = this.fileType;
            templateExportInfo.multiSheet = this.multiSheet;
            templateExportInfo.userInfo = this.userInfo;
            templateExportInfo.sheetNum = this.sheetNum;
            templateExportInfo.paramsList = this.paramsList;
            templateExportInfo.dataListKey = this.dataListKey;
            templateExportInfo.preDir = this.preDir;
            return templateExportInfo;
        }
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getExportType() {
        return exportType;
    }

    public boolean isMultiSheet() {
        return multiSheet;
    }

    public int getSheetNum() {
        return sheetNum;
    }

    public String getExcelId() {
        return excelId;
    }

    public String getPreDir() {
        return preDir;
    }

    public String getFileType() {
        return fileType;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public List<Map<String, Object>> getParamsList() {
        return paramsList;
    }

    public String getDataListKey() {
        return dataListKey;
    }

    public ExcelExportListService getExcelExportListService() {
        return excelExportListService;
    }
}
