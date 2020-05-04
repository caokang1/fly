package com.cignacmb.smart.base.utils.excel;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.cignacmb.smart.base.utils.basic.DateUtils;
import com.cignacmb.smart.base.utils.basic.StringUtils;
import com.cignacmb.smart.base.utils.exception.BusinessException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.*;

/**
 * @author r9wuxx
 *
 * 该方法仅供SMART模板式Excel下载使用
 */
public class ExcelExportTemplateServer {

    /**
     * 真正写入的workbook
     */
    private SXSSFWorkbook workbook;

    /**
     * 模板
     */
    private XSSFWorkbook template;

    /**
     * foreach EL表达式所在行
     */
    private int elForEachRow;

    /**
     * foreach EL表达式的内容
     * key:		ColumnIndex
     * value:   ExcelELInfo
     */
    private Map<Integer, ExcelELInfo> elForEachMap;

    /**
     * 每个sheet最大数据行数
     */
    private int sheetSize = 950000;

    /**
     * 每次分页查询数据
     */
    private int limit = 3000;

    /**
     * 最大sheet，用于分页时的clone数量
     */
    private int maxSheet = 5;

    /**
     * 当前sheet下标
     */
    private int curSheet = 0;

    /**
     * 当前写入数据时的计数器
     */
    private int curCount = 0;

    /**
     * 总计数器
     */
    private int totalCount = 0;

    /**
     * 当前sheet的当前行下标
     */
    private int curRow;

    public ExcelExportTemplateServer(TemplateExportParams templateExportParams) throws Exception{

        this.template = this.getTemplateWorkbook(templateExportParams);
        this.initELForEach();
        this.cloneTemplateSheet();
        this.workbook = new SXSSFWorkbook(this.template);

    }

    /**
     * 克隆excel防止操作原对象,workbook无法克隆,只能对excel进行克隆
     * @param templateExportParams
     * @return
     * @throws Exception
     */
    private XSSFWorkbook getTemplateWorkbook(TemplateExportParams templateExportParams) throws Exception{
        return new XSSFWorkbook(new File(templateExportParams.getTemplateUrl()));
    }

    /**
     * 获取EL表达式
     */
    private void initELForEach(){

        Sheet sheet = this.template.getSheetAt(this.curSheet);

        for (int i = 0; i <= sheet.getLastRowNum(); i++){

            Row row = sheet.getRow(i);

            if(PoiExcelFunction.isBlankRow(row)){
                continue;
            }

            for (int j = 0; j < row.getLastCellNum(); j++){

                Cell cell = row.getCell(j);

                String str = PoiExcelFunction.getValue(cell);

                //找到foreach表达式的开头
                if (!StringUtils.isEmpty(str)
                        && str.indexOf(ExcelConst.EL_START_STR.getValue()) > -1
                        && str.contains(ExcelConst.EL_FOREACH.getValue())){

                    //提取foreach表达式的相关信息
                    this.initForEach(row, j);
                    break;

                }

            }

            if(this.elForEachMap != null && this.elForEachMap.size() > 0) {
                break;
            }

        }

    }

    /**
     * 记录EL表达式的内容
     * @param row
     * @param startColumn
     */
    private void initForEach(Row row, int startColumn){

        this.elForEachRow = row.getRowNum();
        this.curRow = this.elForEachRow;
        this.elForEachMap = new TreeMap<>();

        for (int i = startColumn; i < row.getLastCellNum(); i++){

            Cell cell = row.getCell(i);

            String el = cell.getStringCellValue();

            if(StringUtils.isEmpty(el)){
                continue;
            }

            this.elForEachMap.put(i, PoiExcelFunction.getExcelELEntity(cell));

            if(el.contains(ExcelConst.EL_END_STR.getValue())){
                break;
            }

        }

    }

    /**
     * 对sheet做一些预处理
     */
    private void cloneTemplateSheet(){
        if(this.elForEachMap != null && this.elForEachMap.size() > 0) {
            //将EL表达式所在行删除
            this.template.getSheetAt(this.curSheet).shiftRows(this.curRow + 1, this.curRow + 1,-1);

            //由于SXSSFWorkbook只能逐行往下走，不能复制模板sheet，因此预先在模板中多clone几个sheet
            //将sheet复制到5个，可以支持接近500万的数据，实际基本不可能达到这个数据量
            for (int i = 1; i < this.maxSheet; i++){
                this.template.cloneSheet(this.curSheet);
            }
        }
    }

    /**
     * 生成workbook
     * @return
     * @throws Exception
     */
    public Workbook exportWorkbook(ExcelExportListService excelExportListService, Map<String, Object> params, String key) throws Exception{
        int page = 1;
        String _key = StringUtils.isEmpty(key) ? ExcelConst.EXPORT_DATALIST_KEY.getValue() : key;

        while (true){
            List<Map<String, Object>> list = excelExportListService.selectExcelList(params, page, this.limit);

            if (list == null || list.size() == 0){
                break;
            }

            this.appendData(list, _key);

            if (page > 1 && (list == null || list.size() < this.limit)){
                break;
            }

            page++;
        }

        if (0 == this.totalCount){
            throw new BusinessException("Export Excel is no data");
        }

        this.close();
        return this.workbook;
    }

    /**
     * 分批次写入数据
     * @param list
     * @param key
     * @throws Exception
     */
    private void appendData(List<Map<String, Object>> list, String key) throws Exception{

        if (list != null && list.size() > 0){

            if (this.curCount + list.size() > this.sheetSize){

                this.parseList2Workbook(this.workbook.getSheetAt(this.curSheet), list.subList(0, this.sheetSize - this.curCount), key);
                list.subList(0, this.sheetSize - this.curCount).clear();
                this.curCount = 0;
                this.curRow = this.elForEachRow;
//                this.workbook.createSheet();
                this.curSheet += 1;

                this.appendData(list, key);
            }
            else{

                this.parseList2Workbook(this.workbook.getSheetAt(this.curSheet), list, key);
                this.curCount += list.size();

            }

            this.totalCount += list.size();

        }

    }

    /**
     * 根据EL表达式将数据写入workbook
     * @param sheet
     * @param list
     * @param key
     * @throws Exception
     */
    private void parseList2Workbook(Sheet sheet, List<Map<String, Object>> list, String key) throws  Exception{
        if (list == null || list.size() == 0){
            return;
        }

        for (int i = 0; i < list.size(); i++){

            Row row = sheet.getRow(this.curRow) == null ? sheet.createRow(this.curRow) : sheet.getRow(this.curRow);

            for (Map.Entry<Integer, ExcelELInfo> entry : this.elForEachMap.entrySet()){
                int columnIndex = entry.getKey();
                ExcelELInfo excelELInfo = entry.getValue();
                Map<String, Object> dataMap = list.get(i);

                Cell cell = row.createCell(columnIndex);

                this.setCellValue(cell, excelELInfo, dataMap);

            }

            this.curRow++;
        }
    }

    /**
     * 将值写入cell中
     * @param cell
     * @param excelELInfo
     * @param dataMap
     * @throws Exception
     */
    private void setCellValue(Cell cell, ExcelELInfo excelELInfo, Map<String, Object> dataMap) throws Exception{
        String value = this.getValueFromDataMap(excelELInfo, dataMap);

        if (excelELInfo.getCellStyle() != null){
            cell.setCellStyle(excelELInfo.getCellStyle());
        }

        if (StringUtils.isEmpty(value)){
            return;
        }

        if (!excelELInfo.isNumber()){
            cell.setCellValue(value);
        }
        else {
            cell.setCellValue(Double.valueOf(value));
        }
    }

    /**
     * 从datamap中获取对应的值
     * @param excelELInfo
     * @param dataMap
     * @return
     * @throws Exception
     */
    private String getValueFromDataMap(ExcelELInfo excelELInfo, Map<String, Object> dataMap) throws Exception{

        Object o = dataMap.get(excelELInfo.getKey());

        if (o == null){
            o = dataMap.get(excelELInfo.getKey().toUpperCase());

            if(o == null) {
                o = dataMap.get(excelELInfo.getKey().toLowerCase());

                if(o == null) {
                    return "";
                }
            }
        }

        if (!excelELInfo.isNumber() && !StringUtils.isEmpty(excelELInfo.getFormat())){
            return DateUtils.getDateToStr(excelELInfo.getFormat(), DateUtils.parseDate(o));
        }
        else{
            return o.toString();
        }

    }

    /**
     * 将多余的sheet删除
     */
    private void close(){
        for (int i = this.maxSheet; i > this.curSheet; i--){
            this.workbook.removeSheetAt(i);
        }
    }

    public static void main(String[] args){

        TemplateExportParams templateExportParams = new TemplateExportParams();
        templateExportParams.setTemplateUrl("D:\\easypoitest\\model2.xlsx");
        Workbook wb = null;

        try {
            ExcelExportTemplateServer excelExportTemplateServer = new ExcelExportTemplateServer(templateExportParams);
            wb = excelExportTemplateServer.exportWorkbook(new ExcelExportListService() {
                @Override
                public List<Map<String, Object>> selectExcelList(Map<String, Object> params, int page, int limit) {
                    if (page >= 3){
                        return null;
                    }

                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

                    for(int i = 0; i < 100; i++) {

                        Map<String, Object> map = new HashMap<String, Object>();
                        int index = (page - 1) * 100 + i;

                        map.put("agentcode", "agentcode" + index);
                        map.put("agentnum", "agentnum" + index);

                        if(i % 3 == 0) {
                            map.put("agentstate", null);
                        }
                        else {
                            map.put("agentstate", "agentstate" + index);
                        }

                        map.put("branchtype", "01");
                        map.put("managecom", "86010101");
                        map.put("testnum", 100*index);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.DATE, index);
                        map.put("employdate", calendar.getTime());

                        list.add(map);

                    }

                    return list;
                }
            }, null, "dataList");

            ExcelUtils.saveExcel("D:\\easypoitest\\test1.xlsx", wb);
            System.out.println("done");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
