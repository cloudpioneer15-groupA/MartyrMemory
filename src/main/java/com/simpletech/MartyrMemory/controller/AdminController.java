package com.simpletech.MartyrMemory.controller;

import com.simpletech.MartyrMemory.model.Message;
import com.simpletech.MartyrMemory.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpletech.MartyrMemory.annotations.Intent;
import com.simpletech.MartyrMemory.controller.base.GeneralController;
import com.simpletech.MartyrMemory.model.Admin;
import com.simpletech.MartyrMemory.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 数据库表admin 的Controller层实现
 * @author zimu
 * @date 2015-11-16 14:34:13 中国标准时间
 */
@RestController
@Intent("数据库表admin")
@RequestMapping("/admin")
public class AdminController extends GeneralController<Admin>{

	@Autowired
	AdminService service;
	
	/**
	 * 添加信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("add")
	public Object add(@RequestBody Admin model) throws Exception {
		service.insert(model);
		return null;
	}

	/**
	 * 更新信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("update")
	public Object update(@RequestBody Admin model) throws Exception {
		service.update(model);
		return null;
	}

	/**
	 * 根据ID获取信息
	 * @param ID
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("get/{ID}")
	public Object get(@PathVariable String ID) throws Exception {
		Object model = service.findById(ID);
		if (model == null) {
			return "null";
		}
		return model;
	}

	/**
	 * 根据ID删除
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("delete/{ID}")
	public Object delete(@PathVariable String ID) throws Exception {
		service.delete(ID);
		return null;
	}

	/**
	 * 统计全部
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("count")
	public Object count() throws Exception {
		return service.countAll();
	}

	/**
	 * 获取全部列表
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("list")
	public Object list() throws Exception {
		return service.findAll();
	}
	/**
	 * 获取分页列表
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("list/{pageSize}/{pageNo}")
	public Object listByPage(@PathVariable int pageSize,@PathVariable int pageNo) throws Exception {
		return service.listByPage(pageSize, pageNo);
	}

	/**
	 * 管理员登录
	 * @param name 帐号
	 * @param password 密码
	 * @param request 请求
	 * @param response 响应
	 * @throws Exception SQL异常
	 */
	@RequestMapping("/login")
	public void findByNameAndPassword(String name, String password,String flag,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Admin> adminList = service.findByNameAndPassword(name, password);
		if(adminList != null && adminList.size() > 0){
			request.getSession().setAttribute(Constant.SYS_ADMIN_NAME, adminList.get(0));
			//记录日志
			//跳转
			response.sendRedirect(request.getContextPath() + "/message/list/"+ Message.UNCHECKED_MSG +"/0/0");
		}else{
			request.setAttribute("login_error", "用户名或者密码有误！");
			request.getRequestDispatcher( "/views/manage/login.jsp").forward(request, response);
		}
	}

	/**
	 * 管理员安全退出
	 * @param request 请求
	 * @param response 响应
	 * @throws Exception SQL异常
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute(Constant.SYS_ADMIN_NAME) != null) {
			session.removeAttribute(Constant.SYS_ADMIN_NAME);
			//记录日志
		}
			//跳转
		response.sendRedirect(request.getContextPath() + "/views/manage/login.jsp");
	}
}
