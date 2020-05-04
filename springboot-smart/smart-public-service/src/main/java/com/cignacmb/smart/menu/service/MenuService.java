package com.cignacmb.smart.menu.service;

import com.cignacmb.smart.base.common.PageInfo;
import com.cignacmb.smart.base.common.ResponseData;
import com.cignacmb.smart.base.common.UserInfo;

import java.util.List;

public interface MenuService {
	
	ResponseData validateUser(UserInfo userInfo)throws Exception ;

	List<UserInfo> queryUserList(PageInfo<UserInfo> pageInfo, UserInfo userinfo);
	
	List<Object> selectMenuList(String usercode);
	
}
