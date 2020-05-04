package com.cignacmb.smart.base.utils.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cignacmb.smart.base.annotation.FieldDefaultValue;
import com.cignacmb.smart.base.annotation.FieldRely;
import com.cignacmb.smart.base.annotation.excel.ColumnMapFields;
import com.cignacmb.smart.base.annotation.excel.ColumnType;
import com.cignacmb.smart.base.annotation.excel.Excel;
import com.cignacmb.smart.base.annotation.excel.ExcelColumn;
import com.cignacmb.smart.base.utils.basic.ClassUtils;
import com.cignacmb.smart.base.utils.basic.DateUtils;
import com.cignacmb.smart.base.utils.basic.StringUtils;
import com.cignacmb.smart.entity.base.BaseExcelImportEntity;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.HibernateValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelImportVerifyServer {

    private  Validator validator;

    public ExcelImportVerifyServer(){
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory();
        validator = factory.getValidator();
    }

    public <T> TemplateImportResult<T> importExcel(TemplateImportInfo template) throws Exception{
        TemplateImportResult<T> result = new TemplateImportResult<>();

        if(template == null) {
            result.setImportMsg("importExcel template is null");
            return result;
        }

        if(template.isNeedVerify()){
            if(!(template.getPojoClass().newInstance() instanceof BaseExcelImportEntity)) {
                result.setImportMsg("importExcel pojoClass father must be BaseExcelImportEntity");
                return result;
            }
        }

        if(StringUtils.isEmpty(template.getFilePath()) && template.getInputStream() == null) {
            result.setImportMsg("importExcel is null");
            return result;
        }

        Class<?> pojoClass = template.getPojoClass();
        int startIndex = template.getStartIndex();
        boolean isNeedVerify = template.isNeedVerify();

        boolean verify = true;
        int lastRowNum = 0;
        List<T> list = new ArrayList<>();
        List<String> errorList = new ArrayList<String>();

        //获取excel注解
        Excel excel = pojoClass.getAnnotation(Excel.class);
        //必须要有excel注解
        if(excel == null) {
            result.setImportMsg("importExcel annotation Excel is null");
            return result;
        }

        //1、将file生成workbook
        Workbook workbook = null;

        if(StringUtils.isNotEmpty(template.getFilePath())){
            workbook = WorkbookFactory.create(new File(template.getFilePath()));
        }
        else {
            workbook = WorkbookFactory.create(template.getInputStream());
        }

        if (workbook == null){
            result.setImportMsg("create workbook fail");
            return result;
        }

        Sheet sheet = workbook.getSheetAt(template.getSheetIndex());

        lastRowNum = sheet.getLastRowNum() + 1;

        if(lastRowNum - startIndex == 0) {
            //说明excel没有任何数据
            return result;
        }
        else {
            //获取ColumnFields注解
            ColumnMapFields columnMapFields = pojoClass.getAnnotation(ColumnMapFields.class);
            //获取表头
            Map<String, Integer> titleMap = getTitleMap(sheet.getRow(template.getTitleIndex()));
            //获取JavaBean的字段
            Field[] fields = ClassUtils.getClassFields(pojoClass);
            //获取列号对应的field字段，key=columnindex，value=fieldname
            Map<Integer, String> columnFieldMap = new TreeMap<Integer, String>();
            //key=fieldname，value=columnindex
            Map<String, Integer> fieldColumnMap = new HashMap<String, Integer>();
            //将注解ExcelColumn保存
            Map<Integer, ExcelColumn> excelColumnMap = new HashMap<Integer, ExcelColumn>();
            //将fields转换成map，key=fieldname，value=field
            Map<String, Field> fieldMap = new HashMap<String, Field>();
            //一些需要额外初始化的信息
            Map<String, Object> initMap = template.getInitMap();

            transferFields(excel, columnMapFields, fields, titleMap, columnFieldMap, fieldColumnMap, excelColumnMap, fieldMap);

            //如果没有指定任何列号，则直接返回
            if(columnFieldMap == null || columnFieldMap.size() == 0) {
                result.setImportMsg("importExcel ExcelID == " + excel.excelId() + " columnFieldMap is null");
                return result;
            }

            result.setExcelId(excel.excelId());

            //逐行读取
            for(int index = startIndex; index < lastRowNum; index++) {

                Row row = sheet.getRow(index);

                if(PoiExcelFunction.isBlankRow(row)) {
                    continue;
                }

                //到了最后一行跳出
                if(row.getRowNum() >= lastRowNum) {
                    break;
                }

                //值转换
                T t = setFieldValue(row, columnFieldMap, excelColumnMap, fieldMap, pojoClass, fields, initMap);

                if(t == null) {
                    continue;
                }

                if(isNeedVerify) {
//                    boolean tempVerify = customValidate(t, row.getRowNum(), fieldColumnMap, errorList);

                    verify = verify && customValidate(t, row.getRowNum(), fieldColumnMap, errorList);
                }

                list.add(t);

            }
        }

        if(!isNeedVerify) {
            result.setVerify(true);
        }
        else {
            result.setVerify(verify);
        }

//		if(errorList != null && errorList.size() > 0) {
//			writeErrorMsg2Excel(workbook, errorList);
//		}

        result.setList(list);
        result.setErrorList(errorList);
        result.setImportFlag(true);
        result.setWb(workbook);

        return result;
    }

    /**
     * 获取excel的表头名称及对应的列号
     * @param row
     * @return
     */
    private Map<String, Integer> getTitleMap(Row row){

        Map<String, Integer> titleMap = new HashMap<String, Integer>();

        if(!PoiExcelFunction.isBlankRow(row)) {

            for(int i = row.getFirstCellNum(), len = row.getLastCellNum(); i < len; i++) {
                titleMap.put(StringUtils.trim(PoiExcelFunction.getValue(row.getCell(i))), i);
            }

        }

        return titleMap;

    }

    /**
     *
     * @param excel
     * @param columnMapFields
     * @param fields
     * @param titleMap
     * @param columnFieldMap		key:列号			value:fieldname
     * @param fieldColumnMap		key:fieldName	value:列号
     * @param excelColumnMap		key:列号			value:注解excelColumn
     * @param fieldMap				key:fieldName	value:field
     * @throws Exception
     */
    private void transferFields(Excel excel, ColumnMapFields columnMapFields, Field[] fields, Map<String, Integer> titleMap,
                                       Map<Integer, String> columnFieldMap, Map<String, Integer> fieldColumnMap, Map<Integer, ExcelColumn> excelColumnMap, Map<String, Field> fieldMap) throws Exception{

        for(int i = 0, len = fields.length; i < len; i++) {
            ExcelColumn excelColumn = fields[i].getAnnotation(ExcelColumn.class);

            //使用字段名称进行标记
            if(excel.mapping() == Excel.Mapping.field && columnMapFields != null &&
                    columnMapFields.mappings() != null && columnMapFields.mappings().length > 0) {
                String[] mappings = columnMapFields.mappings();

                for(int j = 0; j < mappings.length; j++) {

                    if(mappings[j].equals(fields[i].getName())) {

                        if(excelColumn != null) {
                            //标记列号对应的fieldname
                            columnFieldMap.put(j, fields[i].getName());
                            fieldColumnMap.put(fields[i].getName(), j);
                            excelColumnMap.put(j, excelColumn);

                        }

                        break;
                    }
                }
            }
            //使用列下标进行标记
            else if(excel.mapping() == Excel.Mapping.index) {

                if(excelColumn != null && excelColumn.index() >= 0) {
//					System.out.println(excelColumn.column() + "****" + fields[i].getName());
                    //标记列号对应的fieldname
                    columnFieldMap.put(excelColumn.index(), fields[i].getName());
                    fieldColumnMap.put(fields[i].getName(), excelColumn.index());
                    excelColumnMap.put(excelColumn.index(), excelColumn);

                }

            }
            //使用表头进行标记
            else if(excel.mapping() == Excel.Mapping.title) {

                if(excelColumn != null) {

                    String columnTitle = StringUtils.trim(excelColumn.title());

                    if(columnTitle != null && !"".equals(columnTitle) &&
                            titleMap.containsKey(columnTitle)) {

                        columnFieldMap.put(titleMap.get(columnTitle), fields[i].getName());
                        fieldColumnMap.put(fields[i].getName(), titleMap.get(columnTitle));
                        excelColumnMap.put(titleMap.get(columnTitle), excelColumn);

                    }

                    //兼容标题带*号
                    columnTitle = columnTitle + "*";

                    if(titleMap.containsKey(columnTitle)) {

                        columnFieldMap.put(titleMap.get(columnTitle), fields[i].getName());
                        fieldColumnMap.put(fields[i].getName(), titleMap.get(columnTitle));
                        excelColumnMap.put(titleMap.get(columnTitle), excelColumn);

                    }

                }

            }

            //标记fieldname对应的field
            fieldMap.put(fields[i].getName(), fields[i]);
        }

    }

    /**
     * 将excel的行转换成bean
     * @param row
     * @param columnFieldMap
     * @param excelColumnMap
     * @param fieldMap
     * @param pojoClass
     * @param fields
     * @param initMap
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private <T> T setFieldValue(Row row, Map<Integer, String> columnFieldMap,
                                       Map<Integer, ExcelColumn> excelColumnMap, Map<String, Field> fieldMap,
                                       Class<?> pojoClass, Field[] fields, Map<String, Object> initMap) throws Exception{

        Object o = null;
        JSONObject jsonObject = new JSONObject();

        try {

            for(int i = row.getFirstCellNum(), last = row.getLastCellNum(); i < last; i++) {

                String fieldName = columnFieldMap.get(i);

                if(StringUtils.isEmpty(fieldName)) {
                    continue;
                }

                Cell cell = row.getCell(i);

                //获取字段值
                String cellValue = PoiExcelFunction.getValue(cell);
                //进行本地化的转换
                cellValue = dealLocateTransfer(cellValue, i, excelColumnMap);

                jsonObject.put(fieldName, cellValue);

            }

            dealDefaultValue(jsonObject, fields, initMap);

            o = pojoClass.newInstance();
            o = JSONObject.toJavaObject(jsonObject, pojoClass);

            if(o instanceof BaseExcelImportEntity) {
                BaseExcelImportEntity e = (BaseExcelImportEntity) o;
                e.setRowno(row.getRowNum());
                e.setColumnFieldMap(columnFieldMap);
            }

        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }

        return (T) o;
    }

    /**
     * 自定义校验
     * @param o
     * @param rowIndex
     * @param fieldColumnMap
     * @param errorList
     * @return
     */
    private boolean customValidate(Object o, int rowIndex, Map<String, Integer> fieldColumnMap, List<String> errorList) {

        StringBuilder sb = new StringBuilder();

        Map<Integer, String> errorMap = new TreeMap<Integer, String>();

        Set<ConstraintViolation<Object>> set = validator.validate(o);

        for (ConstraintViolation<Object> constraintViolation : set) {

            String fieldName = constraintViolation.getPropertyPath().toString();

            //字段级别校验
            if(fieldColumnMap.containsKey(fieldName)) {
                int columnIndex = fieldColumnMap.get(fieldName);

                String errorMsg = errorMap.get(columnIndex);

                sb.setLength(0);

                if(StringUtils.isNotEmpty(errorMsg)) {
                    sb.append(errorMsg).append(",").append(constraintViolation.getMessage());
                }
                else {
                    sb.append(constraintViolation.getMessage());
                }

                errorMap.put(columnIndex, sb.toString());

            }
            //类或者方法校验
            else {
//				System.out.println(constraintViolation.getConstraintDescriptor().getAttributes().toString());

                Object annotation = constraintViolation.getConstraintDescriptor().getAnnotation();

                if(annotation instanceof FieldRely) {
                    FieldRely fieldRely = (FieldRely) annotation;
                    String currentFieldName = fieldRely.currentField();

                    if(fieldColumnMap.containsKey(currentFieldName)) {
                        int columnIndex = fieldColumnMap.get(currentFieldName);

                        String errorMsg = errorMap.get(columnIndex);

                        sb.setLength(0);

                        if(StringUtils.isNotEmpty(errorMsg)) {
                            sb.append(errorMsg).append(",").append(constraintViolation.getMessage());
                        }
                        else {
                            sb.append(constraintViolation.getMessage());
                        }

                        errorMap.put(columnIndex, sb.toString());
                    }

                }

            }

        }

        if(o instanceof BaseExcelImportEntity) {
            BaseExcelImportEntity e = (BaseExcelImportEntity) o;

            if(errorMap != null && errorMap.size() > 0) {
                e.setIsvalid(false);

                sb.setLength(0);
//				StringBuilder sb = new StringBuilder();

                Iterator<Integer> iter = errorMap.keySet().iterator();
                while(iter.hasNext()) {
                    Integer key = iter.next();

                    sb.setLength(0);
                    sb.append("第").append(rowIndex+1).append("行")
                            .append("第").append(key+1).append("列：")
                            .append(errorMap.get(key));

                    errorList.add(sb.toString());
                }
            }

            e.setErrorMap(errorMap);
        }

        return errorMap == null || errorMap.size() == 0;

    }

    /**
     * 根据业务对字段值进行处理
     * @param cellValue
     * @param columnIndex
     * @param excelColumnMap
     * @return
     */
    private String dealLocateTransfer(String cellValue, int columnIndex, Map<Integer, ExcelColumn> excelColumnMap) {

        //根据实际业务，必须对某些类型的字段值进行处理
        if(excelColumnMap.get(columnIndex) != null && !StringUtils.isEmpty(cellValue)) {

            ExcelColumn excelColumn = excelColumnMap.get(columnIndex);

            //如果excel中的内容不能转换成日期，则使用该值替代，便于后续的validation可以正常进行，
            //而不是在set JavaBean的时候报转换异常
            if(excelColumn.type() == ColumnType.Type.date) {
                if(DateUtils.isSmartDate(cellValue)) {
                    cellValue = DateUtils.getSmartDateStr(cellValue);
                }
                else {
                    //如果不符合SMART约定的日期格式，则使用该值标记日期格式错误
                    cellValue = ExcelConst.VALID_DATE_FALSE.getValue();
                }
            }
            //如果excel中的内容不能转换成数字，则使用该值替代，便于后续的validation可以正常进行，
            //而不是在set JavaBean的时候报转换异常
            else if(excelColumn.type() == ColumnType.Type.number) {
                if(!NumberUtils.isCreatable(cellValue)) {
                    cellValue = ExcelConst.VALID_NUMBER_FALSE.getValue();
                }
            }
            //excel中如果是下拉枚举值，其数据形态如“01-XX”，需要将值转换成“01”，默认以“-”为分隔符
            else if(excelColumn.type() == ColumnType.Type.enums) {
                if(cellValue.indexOf("-") >= 0) {
                    cellValue = StringUtils.split(cellValue, "-")[0];
                }
            }

        }

        return cellValue;

    }

    /**
     * 处理默认值
     * @param jsonObject
     * @param fields
     * @param initMap
     */
    private void dealDefaultValue(JSONObject jsonObject, Field[] fields, Map<String, Object> initMap) {

        for(int i = 0, len = fields.length; i < len; i++) {

            Field field = fields[i];
            String fieldName = field.getName();
            FieldDefaultValue fieldDefaultValue = field.getAnnotation(FieldDefaultValue.class);

            if(fieldDefaultValue != null && !"".equals(fieldDefaultValue.value())) {

                if(!jsonObject.containsKey(fieldName)) {
                    jsonObject.put(fieldName, fieldDefaultValue.value());
                }
                else {
                    if(StringUtils.isEmpty(jsonObject.getString(fieldName))) {
                        jsonObject.put(fieldName, fieldDefaultValue.value());
                    }
                }

            }

            if(initMap != null && initMap.containsKey(fieldName)) {

                if(!jsonObject.containsKey(fieldName)) {
                    jsonObject.put(fieldName, initMap.get(fieldName));
                }
                else {
                    if(StringUtils.isEmpty(jsonObject.getString(fieldName))) {
                        jsonObject.put(fieldName, initMap.get(fieldName));
                    }
                }

            }

        }

    }

    /**
     * 导入demo
     */
    private void excelImportDemo() {
        List<ExcelDemo> list;

        try {
            TemplateImportInfo template = TemplateImportInfo.Builder.aTemplateImportInfo()
                    .withFilePath("D:\\easypoitest\\test.xlsx")
                    .withPojoClass(ExcelDemo.class)
                    .withStartIndex(2)
                    .withTitleIndex(1)
                    .build();
//            Workbook workbook = WorkbookFactory.create(new File(template.getFilePath()));
//            File file = new File(template.getFilePath());
//            Workbook workbook = new XSSFWorkbook(file);
//            workbook.getSheetAt(0).getLastRowNum();
//            TemplateImportInfo template = new TemplateImportInfo.Builder()
//                    .file("D:\\easypoitest\\test.xlsx")
//                    .pojoClass(ExcelDemo.class)
//                    .startIndex(2)
//                    .titleIndex(1)
//                    .build();

            TemplateImportResult<ExcelDemo> result = importExcel(template);

            list = result.getList();

            System.out.println(list.size());
            for(int i = 0; i < list.size(); i++) {
                System.out.println(JSON.toJSONString(list.get(i)) + " *** " + DateUtils.getDateStr(list.get(i).getEmploydate()));
            }

            for(int i = 0; i < result.getErrorList().size(); i++) {
                System.out.println(JSON.toJSONString(result.getErrorList().get(i)));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExcelImportVerifyServer excelImportVerifyServer = new ExcelImportVerifyServer();
        excelImportVerifyServer.excelImportDemo();
//        TemplateExportParams t = new TemplateExportParams();
//        System.out.println("123");
    }

}
