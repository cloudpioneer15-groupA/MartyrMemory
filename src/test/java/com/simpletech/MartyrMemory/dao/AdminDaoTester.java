package com.simpletech.MartyrMemory.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.MartyrMemory.model.Admin;
import com.simpletech.MartyrMemory.util.JacksonUtil;

/**
 * 数据库表admin的Dao层测试类
 * @author zimu
 * @date 2015-11-16 14:34:14 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class AdminDaoTester {

	@Autowired
	AdminDao dao;
	
	@Test
	public void insert() throws Exception{
		Admin model = new Admin();
		/*model.setAdminName("admin");
		model.setPassword("123456");
		model.setDeleteFlag(false);
		model.setIsLogin(0);*/
		Object result = dao.insert(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void update() throws Exception {
		Admin model = new Admin();
		Object result = dao.update(model);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void delete() throws Exception {
		Object result = dao.delete("1");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
	@Test
	public void countAll() throws Exception {
		Object result = dao.countAll();
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void findByPage() throws Exception {
		Object result = dao.findByPage(5,0);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}
	
}
