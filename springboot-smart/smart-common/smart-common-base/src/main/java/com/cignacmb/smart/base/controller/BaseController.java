package com.cignacmb.smart.base.controller;

import com.cignacmb.smart.base.common.UserInfo;
import com.cignacmb.smart.base.config.Commons;
import com.cignacmb.smart.base.config.GlobalInput;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 实现公共模块相关功能
 *
 * @author like
 *
 */

public  class BaseController {


    public HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {}
        return session;
    }

    /* 用户信息加入session
     *
     * @param userInfo
     */
    public void setUserInfo(UserInfo userInfo, GlobalInput tG) {
        if (null != userInfo) {
            getRequest().getSession().setAttribute(Commons.SESSION_GI,tG);
            getRequest().getSession().setAttribute(Commons.SESSION_USERINFO, userInfo);
            getRequest().getSession().setMaxInactiveInterval(Commons.SESSION_MaxInactiveInterval);// 设置session超时时间
        }
    }

    /**
     * 从session中获取用户信息
     *
     * @return
     */
    public UserInfo getUserInfo() {
        ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        if(attrs != null) {
            return (UserInfo) attrs.getRequest().getSession().getAttribute(Commons.SESSION_USERINFO);
        }
        else {
            return null;
        }
//		UserInfo userInfo = (UserInfo)  getRequest().getSession().getAttribute(Commons.SESSION_USERINFO);
//		return (userInfo != null ? userInfo : null);
    }

    /**
     * 清除session
     *
     */
    public void clearSession() {
        getRequest().getSession().removeAttribute(Commons.SESSION_USERINFO);
        getRequest().getSession().removeAttribute(Commons.SESSION_GI);
        getRequest().getSession().removeAttribute("GI");
        getRequest().getSession().removeAttribute("GI");
        getRequest().getSession().removeAttribute("GI");
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

}

