package com.cignacmb.smart.base.interceptor;

import com.cignacmb.smart.base.common.UserInfo;
import com.cignacmb.smart.base.config.Commons;
import com.cignacmb.smart.base.utils.exception.BusinessException;
import lombok.extern.java.Log;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: caokang
 * @Date: Created in 10:37 2020/4/30
 * @annotation:session拦截器
 * @version:1.0
 */
@Log
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object Headler) throws Exception {
        String url = request.getRequestURI();
        String context = request.getServletContext().getContextPath();
        log.info(url);
//        uri = uri.substring(context.length());
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Commons.SESSION_USERINFO);
        if (userInfo == null) {
            throw new BusinessException("登录已失效，请重新登录");
            // TODO: 2020/4/30 session过期后期需要改写token过期的方式
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
                           Object paramObject, ModelAndView paramModelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest paramHttpServletRequest,
                                HttpServletResponse paramHttpServletResponse, Object paramObject, Exception paramException)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
