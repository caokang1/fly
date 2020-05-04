package com.cignacmb.smart.base.utils.excel;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.cignacmb.smart.base.utils.file.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /**
     * 根据模板导出Excel，仅支持一个模板
     * @param templateExportParams
     * @param excelExportListService
     * @param params
     * @param dataListKey
     * @return
     * @throws Exception
     */
    public static Workbook createBigExcelByTemplate(TemplateExportParams templateExportParams,
                                                    ExcelExportListService excelExportListService,
                                                    Map<String, Object> params,
                                                    String dataListKey) throws Exception{
//        ExcelExportTemplateServer excelExportTemplateServer = new ExcelExportTemplateServer(templateExportParams);
//
//        return excelExportTemplateServer.exportWorkbook(excelExportService, params, dataListKey);
        return (new ExcelExportTemplateServer(templateExportParams)).exportWorkbook(excelExportListService,params,dataListKey);
    }

    /**
     * 导入Excel，并返回校验结果
     * @param templateImportInfo
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> TemplateImportResult<T> importExcel(TemplateImportInfo templateImportInfo) throws Exception{
        return (new ExcelImportVerifyServer()).importExcel(templateImportInfo);
    }

    /**
     * 在页面直接生成Excel
     * @param fileName
     * @param response
     * @param workbook
     * @throws Exception
     */
    public static void exportExcel(String fileName, HttpServletResponse response, Workbook workbook) throws Exception{
//        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+fileName+"");
        response.setHeader("Pragma", "No-cache");//设置不要缓存
//		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        workbook.write(response.getOutputStream());
    }

    /**
     * 保存Excel
     * @param filePath
     * @param workbook
     * @throws Exception
     */
    public static void saveExcel(String filePath, Workbook workbook) throws Exception{

        String filePath1 = FileUtils.separatorFormat(filePath);

        File file = new File(filePath1);

        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(file)){
            workbook.write(fos);
        }

    }

    /**
     * 错误信息写入workbook
     * @param workbook
     * @param list
     * @throws Exception
     */
    public static void writeErrorMsg2Excel(Workbook workbook, List<String> list){
        if (list == null || list.size() == 0){
            return;
        }

        if (workbook == null){
            return;
        }

        Sheet sheet = workbook.getSheet(ExcelConst.IMPORT_ERROR_SHEET.getValue());

        if (sheet == null){
            sheet = workbook.createSheet(ExcelConst.IMPORT_ERROR_SHEET.getValue());
        }

        int index = sheet.getLastRowNum();

        for (int i = 0; i < list.size(); i++){
            Row row = sheet.createRow(index);
            Cell cell = row.createCell(0);

            cell.setCellValue(list.get(i));

            index++;
        }
    }

}
