package com.cignacmb.smart.base.utils.excel;

import com.cignacmb.smart.base.utils.basic.DateUtils;
import com.cignacmb.smart.base.utils.basic.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.math.BigDecimal;
import java.util.Date;

import static org.apache.poi.ss.usermodel.CellType.*;

public class PoiExcelFunction {

    private PoiExcelFunction(){

    }

    /**
     * 判断是否空行
     * @param row
     * @return
     */
    public static boolean isBlankRow(Row row) {

        boolean flag = true;

        if(row == null) {
            return flag;
        }

        for(int i = row.getFirstCellNum(), len = row.getLastCellNum(); i < len; i++) {

            Cell cell = row.getCell(i);

            if(cell != null && cell.getCellType() != BLANK && !StringUtils.isEmpty(getValue(cell))) {
                flag = false;
                break;
            }
        }

        return flag;

    }

    /**
     * 获取cell的String值
     * @param cell
     * @return
     */
    public static String getValue(Cell cell) {
        String value = "";

        if(cell==null){
            return value;
        }

        switch (cell.getCellType()) {
            case NUMERIC: // 数值型
                if (DateUtil.isCellDateFormatted(cell)) {

//				System.out.println("javadate:"+DateUtil.getJavaDate(cell.getNumericCellValue()));
                    Date d = DateUtil.getJavaDate(cell.getNumericCellValue());
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value = DateUtils.getDateStr(DateUtils.parseDBDate(d));
//				value = DateFormat.getDateInstance(DateFormat.MEDIUM).format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
//				System.out.println("old date :"+value);
//				value = new TMPublicCheck().getStrDate(value);
//				System.out.println("new date :"+value);
                } else {// 纯数字
//				System.out.println("纯数字old:" + cell.getStringCellValue().toString());
//				value = String.valueOf(cell.getNumericCellValue());
//				System.out.println("纯数字new:" + value);
                    BigDecimal tBigDecimal = new BigDecimal(Double.toString(cell.getNumericCellValue()));
                    value = tBigDecimal.toPlainString();

                    if(value.contains(".") && value.endsWith("0") && !value.endsWith(".0")) {
                        value = value.substring(0,value.length()-1);
                    }
                }
                if(value.endsWith(".0")){
                    value=value.substring(0, value.length()-2);
                }
                break;
            case STRING: // 字符串型
//			System.out.println("字符串型old:" + cell.getStringCellValue().toString());
                value = cell.getStringCellValue().toString();
//			System.out.println("字符串型new:" + value);
                break;
            case FORMULA:// 公式型
//			System.out.println("公式型old:" + cell.getStringCellValue().toString());
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
//			System.out.println("公式型new:" + value);
                break;
            case BOOLEAN:// 布尔
//			System.out.println(" 布尔型old:" + cell.getStringCellValue().toString());
                value = "" + cell.getBooleanCellValue();
//			System.out.println("布尔型new:" + value);
                break;
            case BLANK: // 空值
//			System.out.println(" 空值");
                value = "";
                break;
            case ERROR: // 故障
//			System.out.println(" 故障");
                value = "";
                break;
            default:
                value = cell.getStringCellValue();
//			System.out.println(" 默认:"+value);
        }
        return StringUtils.trim(value);
    }

    public static ExcelELInfo getExcelELEntity(Cell cell) {

        ExcelELInfo entity = new ExcelELInfo();
        String el = cell.getStringCellValue();

        entity.setEl(el);
        entity.setCellStyle(cell.getCellStyle());

        String keys = "";

        if(el.contains(ExcelConst.EL_START_STR.getValue())) {
            el = StringUtils.trim(el.split("\\s{1,}")[1]);
        }

        if(el.contains(ExcelConst.EL_END_STR.getValue())) {
            el = el.replaceAll(ExcelConst.EL_END_STR.getValue(), ExcelConst.EL_EMPTY.getValue());
        }

        if(el.contains(ExcelConst.EL_FORMAT_DATE.getValue())) {
            keys = getKey(ExcelConst.EL_FORMAT_DATE.getValue(), el);

            entity.setFormat(StringUtils.trim(keys.split(";")[1]));
            entity.setKey(StringUtils.trim(keys.split(";")[0].split("\\.")[1]));

            return entity;
        }

        if(el.contains(ExcelConst.EL_FORMAT_NUMBER.getValue())) {
            keys = getKey(ExcelConst.EL_FORMAT_NUMBER.getValue(), el);

            entity.setFormat(StringUtils.trim(keys.split(";")[1]));
            entity.setKey(StringUtils.trim(keys.split(";")[0].split("\\.")[1]));
            entity.setNumber(true);

            return entity;
        }

        if(el.contains(ExcelConst.EL_NUMBER_SYMBOL.getValue())) {
            keys = el.replaceAll(ExcelConst.EL_NUMBER_SYMBOL.getValue(), ExcelConst.EL_EMPTY.getValue());

            entity.setKey(StringUtils.trim(keys.split("\\.")[1]));
            entity.setNumber(true);

            return entity;
        }

//		System.out.println("el:" + el);
//		System.out.println(JSON.toJSONString(el.split("\\.")));
        entity.setKey(StringUtils.trim(el.split("\\.")[1]));

        return entity;

    }

    private static String getKey(String prefix, String text) {
        int leftBracket = 1, rightBracket = 0, position = 0;
        int index = text.indexOf(prefix) + prefix.length();
        while (text.charAt(index) == " ".charAt(0)) {
            text = text.substring(0, index) + text.substring(index + 1, text.length());
        }
        for (int i = text.indexOf(prefix + ExcelConst.EL_LEFT_BRACKET.getValue()) + prefix.length() + 1; i < text
                .length(); i++) {
            if (text.charAt(i) == ExcelConst.EL_LEFT_BRACKET.getValue().charAt(0)) {
                leftBracket++;
            }
            if (text.charAt(i) == ExcelConst.EL_RIGHT_BRACKET.getValue().charAt(0)) {
                rightBracket++;
            }
            if (leftBracket == rightBracket) {
                position = i;
                break;
            }
        }

        return text.substring(text.indexOf(prefix + ExcelConst.EL_LEFT_BRACKET.getValue()) + 1 + prefix.length(), position)
                .trim();
    }

}
