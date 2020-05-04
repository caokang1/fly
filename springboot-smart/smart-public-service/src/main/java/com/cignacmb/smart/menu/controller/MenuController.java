package com.cignacmb.smart.menu.controller;

import com.cignacmb.smart.base.common.ResponseData;
import com.cignacmb.smart.base.config.Commons;
import com.cignacmb.smart.base.controller.BaseController;
import com.cignacmb.smart.entity.base.LdCode;
import com.cignacmb.smart.menu.service.LdCodeService;
import com.cignacmb.smart.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author flm
 * @Create 2020-04-29 9:47
 */

@RestController
@RequestMapping("/login")
public class MenuController extends BaseController {

    @Autowired
    LdCodeService ldCodeService;

    @Autowired
    MenuService menuService;

    /**
     * 获取用户菜单组
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectMenuList")
    @ResponseBody
    public ResponseData selectMenuList() throws Exception {
        ResponseData  responseData = new ResponseData() ;
//        if(super.getUserInfo() == null) {
//            return responseData.failWithMsg("session 失效");
//        }

        List<LdCode> codelist = ldCodeService.quseryListByType("theinv");
        List<Object> menulist = menuService.selectMenuList("admin");

        if(codelist.size()>0) {
            responseData.addData("theinv", codelist.get(0).getCodeName());
            responseData.addData(Commons.SESSION_USERINFO, super.getUserInfo());
        }

        responseData.successWithData(menulist);

        return responseData;
    }


}
