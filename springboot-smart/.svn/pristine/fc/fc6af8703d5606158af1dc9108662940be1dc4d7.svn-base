package com.cignacmb.smart.menu.service;

import com.cignacmb.smart.entity.base.LdCode;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface LdCodeService {

	List<LdCode> quseryListByType(String codetype) throws Exception;

	List<LdCode> quserymanagecomByType(String codetype) throws Exception;
	
	List<Map<String, Object>> tmmanagecom(Map<String, Object> parmas) throws Exception;

	List<Map<String, Object>> tmbranchgroup(Map<String, Object> parmas) throws SQLException;
	
	String createMaxNo(String noType, String noLimit) throws SQLException;
	
	List<LdCode> queryListByRecord(LdCode record) throws Exception;
	
	List<LdCode> quseryListByTypeAndCode(String codeType, String code) throws Exception;

}
