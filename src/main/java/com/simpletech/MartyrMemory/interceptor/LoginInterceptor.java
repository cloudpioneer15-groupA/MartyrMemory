package com.simpletech.MartyrMemory.interceptor;

import com.simpletech.MartyrMemory.model.Admin;
import com.simpletech.MartyrMemory.util.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author george zeng
 * @module 后台管理模块
 * ToDo 对登录进行过滤
 * @date 2015/12/13 13:03
 */
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(!uri.contains("/manage/login.jsp")
                && !uri.contains("/admin/login")
                && !uri.contains("/js/")
                && !uri.contains("/css/")
                && !uri.contains("/img/"))
        {

            //放行前台的访问留言列表
            String referer = request.getHeader("referer");
            if(referer != null && !"".equals(referer ) && referer.contains("/views/") && !referer.contains("/manage/")){
                return  true;
            }

            //说明不是登录请求
            Admin admin = (Admin) request.getSession().getAttribute(Constant.SYS_ADMIN_NAME);
            if(admin != null){
                //说明已经登录过
                //初始化其它信息
                return true;
            }else{
                //说明还没有登录过
                //重定向到登录页
                response.sendRedirect(request.getContextPath() + "/views/manage/login.jsp");
                return  false;
            }
        }else{
            //说明是登录请求，直接放行
            return  true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
       // httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/message/list/0/0");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
