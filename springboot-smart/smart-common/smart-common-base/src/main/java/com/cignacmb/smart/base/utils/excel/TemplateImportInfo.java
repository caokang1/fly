package com.cignacmb.smart.base.utils.excel;

import org.springframework.cglib.beans.BulkBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TemplateImportInfo {
    /**
     * java entity对应的class
     */
    private Class<?> pojoClass;

    /**
     * 读取第几个sheet，默认0
     */
    private int sheetIndex;

    /**
     * 标题下标，默认0
     */
    private int titleIndex;

    /**
     * 从第几行开始读取，默认下标1
     */
    private int startIndex;

    /**
     * 模板路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件流
     */
    private InputStream inputStream;

    /**
     * 是否开启校验，默认true
     */
    private boolean isNeedVerify;

    /**
     * 是否开启异步
     */
    private boolean async;

    /**
     * 批次号
     */
    private String batchsn;

    /**
     * 描述
     */
    private String description;

    /**
     * 需要额外初始化至Java bean的内容
     * key：fieldname
     */
    private Map<String, Object> initMap = new HashMap<String, Object>();

    /**
     * 记录校验时需要的参数
     */
    private Map<String, Object> params = new HashMap<String, Object>();

    public static final class Builder {
        private Class<?> pojoClass;
        private int sheetIndex = 0;
        private int titleIndex = 0;
        private int startIndex = 1;
        private String filePath;
        private InputStream inputStream;
        private long fileSize;
        private boolean isNeedVerify = true;
        private boolean async = false;
        private String batchsn;
        private String description;
        private Map<String, Object> initMap = new HashMap<String, Object>();
        private Map<String, Object> params = new HashMap<String, Object>();

        private Builder() {
        }

        public static Builder aTemplateImportInfo() {
            return new Builder();
        }

        public Builder withPojoClass(Class<?> pojoClass) {
            this.pojoClass = pojoClass;
            return this;
        }

        public Builder withSheetIndex(int sheetIndex) {
            this.sheetIndex = sheetIndex;
            return this;
        }

        public Builder withTitleIndex(int titleIndex) {
            this.titleIndex = titleIndex;
            return this;
        }

        public Builder withStartIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        public Builder withFilePath(String filePath) {
            this.filePath = filePath;
            try {
                File file = new File(filePath);
                this.fileSize = file.length();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        public Builder withMultipartFile(MultipartFile multipartFile){
            this.fileSize = multipartFile.getSize();
            try {
                this.inputStream = multipartFile.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public Builder withIsNeedVerify(boolean isNeedVerify) {
            this.isNeedVerify = isNeedVerify;
            return this;
        }

        public Builder withAsync(boolean async) {
            this.async = async;
            return this;
        }

        public Builder withBatchsn(String batchsn) {
//            this.batchsn = batchsn;
//            this.initMap.put(ExcelConst.BATCHSN_KEY.getValue(), batchsn);
//            this.params.put(ExcelConst.BATCHSN_KEY.getValue(), batchsn);
            return withBatchsn(ExcelConst.IMPORT_BATCHSN_KEY.getValue(), batchsn);
        }

        public Builder withBatchsn(String key, String batchsn){
            this.batchsn = batchsn;
            this.initMap.put(key, batchsn);
            this.params.put(key, batchsn);
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withInitMap(Map<String, Object> initMap){
            this.initMap.putAll(initMap);
            return this;
        }

        public Builder withInitMap(String key, Object value) {
            this.initMap.put(key, value);
            return this;
        }

        public Builder withParams(Map<String, Object> params){
            this.params.putAll(params);
            return this;
        }

        public Builder withParams(String key, Object value) {
            this.params.put(key, value);
            return this;
        }

        public TemplateImportInfo build() {
            TemplateImportInfo templateImportInfo = new TemplateImportInfo();
            templateImportInfo.isNeedVerify = this.isNeedVerify;
            templateImportInfo.titleIndex = this.titleIndex;
            templateImportInfo.params = this.params;
            templateImportInfo.filePath = this.filePath;
            templateImportInfo.inputStream = this.inputStream;
            templateImportInfo.fileSize = this.fileSize;
            templateImportInfo.startIndex = this.startIndex;
            templateImportInfo.sheetIndex = this.sheetIndex;
            templateImportInfo.async = this.async;
            templateImportInfo.pojoClass = this.pojoClass;
            templateImportInfo.initMap = this.initMap;
            templateImportInfo.description = this.description;
            templateImportInfo.batchsn = this.batchsn;
            return templateImportInfo;
        }
    }

    public Class<?> getPojoClass() {
        return pojoClass;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public int getTitleIndex() {
        return titleIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public String getFilePath() {
        return filePath;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getFileSize() {
        return fileSize;
    }

    public boolean isNeedVerify() {
        return isNeedVerify;
    }

    public boolean isAsync() {
        return async;
    }

    public String getBatchsn() {
        return batchsn;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Object> getInitMap() {
        return initMap;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
