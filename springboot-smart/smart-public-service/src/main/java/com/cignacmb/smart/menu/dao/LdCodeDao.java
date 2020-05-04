package com.cignacmb.smart.menu.dao;

import com.cignacmb.smart.base.mapper.BaseMapper;
import com.cignacmb.smart.entity.base.LdCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface LdCodeDao extends BaseMapper<LdCode> {

	List<LdCode> quseryListByType(String codetype) throws SQLException;

	List<LdCode> quseryListByTypeAndCode(@Param("codeType") String codeType, @Param("code") String code) throws SQLException;

	List<LdCode> quseryManagecomByType(String codetype) throws SQLException;
	
	List<Map<String, Object>> tmmanagecom(Map<String, Object> parmas) throws SQLException;
	
	List<Map<String, Object>> tmbranchgroup(Map<String, Object> parmas) throws SQLException;
	
	String createMaxNo(@Param("noType") String noType, @Param("noLimit") String noLimit) throws SQLException;

	String queryMaxFreeze(@Param("codeType") String codeType, @Param("code") String code) throws SQLException;
}
