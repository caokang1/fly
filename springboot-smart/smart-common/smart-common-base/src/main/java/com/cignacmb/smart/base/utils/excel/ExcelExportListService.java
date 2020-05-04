package com.cignacmb.smart.base.utils.excel;

import java.util.List;
import java.util.Map;

public interface ExcelExportListService {

    public List<Map<String, Object>> selectExcelList(Map<String, Object> params, int page, int limit);

}
