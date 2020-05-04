package com.cignacmb.smart.menu.service.impl;

import com.cignacmb.smart.entity.base.LdCode;
import com.cignacmb.smart.menu.dao.LdCodeDao;
import com.cignacmb.smart.menu.service.LdCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class LdCodeServiceImpl implements LdCodeService {

	@Autowired
	private LdCodeDao ldCodeDao;
	
	@Override
	public List<LdCode> quseryListByType(String codetype) throws Exception {
		
		return ldCodeDao.quseryListByType(codetype);
	}

	@Override
	public List<LdCode> quserymanagecomByType(String codetype) throws Exception {
		return ldCodeDao.quseryManagecomByType(codetype);
	}
	
	@Override
	public List<Map<String, Object>> tmmanagecom(Map<String, Object> parmas) throws Exception {
		return ldCodeDao.tmmanagecom(parmas);
	}

	@Override
	public List<LdCode> quseryListByTypeAndCode(String codeType, String code) throws Exception {
		return ldCodeDao.quseryListByTypeAndCode(codeType, code);
	}

	@Override
	public List<Map<String, Object>> tmbranchgroup(Map<String, Object> parmas) throws SQLException {
		return ldCodeDao.tmbranchgroup(parmas);
	}

	@Override
	public String createMaxNo(String noType, String noLimit) throws SQLException {
		return ldCodeDao.createMaxNo(noType,noLimit);
	}
	
	@Override
	public List<LdCode> queryListByRecord(LdCode record) throws Exception{
		return ldCodeDao.selectByCondition(record);
	}

}
