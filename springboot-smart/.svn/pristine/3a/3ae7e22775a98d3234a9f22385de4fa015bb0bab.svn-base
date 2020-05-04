package com.cignacmb.smart.menu.service.impl;

import com.cignacmb.smart.base.common.PageInfo;
import com.cignacmb.smart.base.common.ResponseData;
import com.cignacmb.smart.base.common.UserInfo;
import com.cignacmb.smart.menu.dao.MenuDao;
import com.cignacmb.smart.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author flm
 * @Create 2020-04-29 11:31
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public ResponseData validateUser(UserInfo userInfo) throws Exception {
        return null;
    }

    @Override
    public List<UserInfo> queryUserList(PageInfo<UserInfo> pageInfo, UserInfo userinfo) {
        return null;
    }

    @Override
    public List<Object> selectMenuList(String usercode) {
        return menuDao.selectMenuList(usercode);
    }
}
